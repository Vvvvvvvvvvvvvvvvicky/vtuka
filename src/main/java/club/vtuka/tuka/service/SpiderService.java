package club.vtuka.tuka.service;


import club.vtuka.tuka.common.HttpClientUtils;
import club.vtuka.tuka.constant.SysConstant;
import club.vtuka.tuka.mapper.DressCollocationInfoMapper;
import club.vtuka.tuka.mapper.DressCollocationItemMapper;
import club.vtuka.tuka.model.DressCollocationInfo;
import club.vtuka.tuka.model.DressCollocationItem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Vicky on 2018/5/4.
 */
@Service
public class SpiderService {
    private static Logger logger = LoggerFactory.getLogger(SpiderService.class);

    @Autowired
    private DressCollocationInfoMapper infoMapper;
    @Autowired
    private DressCollocationItemMapper itemMapper;

    public void startSpider(String url, Map<String, String> params){
        for(int page=1;page<=1;page++){
            params.put("page",String.valueOf(page));
            String html = HttpClientUtils.sendPost(url, null, params);
            if(!StringUtils.isBlank(html)){
                parseInitHtml(html);
            }
            for(int index=2;index<=5;index++){
                parseAjaxHtml(SysConstant.AJAX_BASE_URL,params,index);
            }

        }
    }

    /**
     * 初始加载页面提取数据
     * @param html
     */
    public void parseInitHtml(String html){
            Document document = Jsoup.parse(html);
//            String parsedHtml = getCharset(documentOrigin, html);
//            Document document = Jsoup.parse(parsedHtml);

            List<DressCollocationInfo> infoList = new ArrayList<>();
            Elements elements = document.select("div[class=tMain br6]").select("div[class=dpjy]").select("div[class=dpjyItem]");

            //每行搭配信息逐一分析
            for(Element element : elements){
                String recommedReason = element.select("div[class=dptLy clearfix]").select("p[class=left ml10]").text();
                Elements tElements = element.select("table").select("tr").select("td[valign=top]");

                Element infoElement = tElements.select("div[class=dpjyImg]").select("a").get(0);
                String collocationUrl = infoElement.attr("href");
                String collocationType = infoElement.attr("title");
                String imgUrl = infoElement.select("img").attr("src");
                //创建info
                if(StringUtils.isBlank(collocationUrl)) continue;
                if(StringUtils.isBlank(imgUrl)) continue;
                if(StringUtils.isBlank(collocationType)) collocationType = "";
                if(StringUtils.isBlank(recommedReason)) recommedReason = "";

                DressCollocationInfo info = new DressCollocationInfo(collocationUrl,imgUrl,collocationType,recommedReason,false,false);

                //查询数据库 有重复数据不重复爬取
                try{
                    infoMapper.insertSelective(info);
                    //分析单品搭配数据
                    Elements itemElements = tElements.select("div[class=dpjySlide]").select("div[class=diOut jCarouselLite]").select("li[class=diDanpin]");
                    List<DressCollocationItem> itemList = new ArrayList<>();
                    for(Element itemElement: itemElements){
                        Element aElement = itemElement.select("a").get(0);
                        String itemUrl = aElement.attr("href");
                        String itemName = aElement.attr("title");
                        String itemImage = aElement.select("img").attr("src");
                        Element pElement = itemElement.select("p").get(0);
                        Elements emElements = pElement.select("em");
                        String itemBrand = emElements.select("a[class=pinpai]").text();
                        String priceString = emElements.select("span").text();
                        BigDecimal itemPrice = BigDecimal.valueOf(Double.valueOf(priceString.substring(0,priceString.length()-1)));
                        Long infoId = info.getId();
                        DressCollocationItem item = new DressCollocationItem(infoId, itemUrl, itemName, itemBrand, itemImage, itemPrice, false);
                        itemList.add(item);
                    }
                    itemMapper.insertBatch(itemList);
                }catch(Exception e){
                    logger.error("爬取插入有误,名字为："+info.getCollocationType());
                    logger.error(e.getMessage());
                    continue;
                }
            }
    }

    public String getCharset(Document document, String html){
        try {
            Element meta = document.getElementsByTag("meta").first();
            if(meta != null){
                String content = null;
                String charset = null;
                if(meta.attr("content") != null && meta.attr("content") != ""){
                    content = meta.attr("content");
                    charset = content.substring(content.indexOf("=")+1);
                }else if(meta.attr("charset") != null && meta.attr("charset") != ""){
                    charset = meta.attr("charset");
                }else{
                    charset = "GBK";
                }
                html = new String(html.getBytes("ISO-8859-1"),charset);
            }else{
                html = new String(html.getBytes("ISO-8859-1"),"GBK");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("获取编码字符类型出错，转换异常");
        }
        return html;
    }

    public void parseAjaxHtml(String url, Map<String,String> params, int togoIndex){
        params.put("m","wdata");
        params.put("a","pxgroup");
        params.put("d","1");
        params.put("c","0");
        params.put("s","0");
        params.put("k","0");
        params.put("p","0");
        params.put("season","4");
        params.put("togo",String.valueOf(togoIndex));
        String ajaxResult = HttpClientUtils.sendGet(url, null, params);
        JSONObject jsonObject = JSON.parseObject(ajaxResult);

        String brands = jsonObject.getString("bds");
        Map<String, String> brandMap = new HashMap<String,String>();
        Map<String,Map<String,String>> brandInfoMap = JSON.parseObject(brands, Map.class);
        for(Map.Entry<String,Map<String,String>> brandEntry:brandInfoMap.entrySet()){
            String brandId = brandEntry.getKey();
            Map<String,String> brandInfo = brandEntry.getValue();
            String brandName = (String) brandInfo.get("name");
            brandMap.put(brandId,brandName);
        }

        String dataInfo = jsonObject.getString("data");
        List<Map<String, Object>> dataInfoList = JSON.parseObject(dataInfo, List.class);
        Iterator<Map<String, Object>> iterator = dataInfoList.iterator();
        while (iterator.hasNext()){
            Map<String, Object> data = iterator.next();
            JSONArray brand = (JSONArray) data.get("brand");
            String collocationUrl = SysConstant.AJAX_BASE_COLLOCATION_URL + (String)data.get("id");
            String collocationImgUrl = SysConstant.AJAX_BASE_URL + (String)data.get("px_pic");
            String collocationType = (String) data.get("title");
            String recommendReason = (String) data.get("reason");

            if(collocationUrl == null) continue;
            if(collocationImgUrl== null) continue;
            if(collocationType == null) collocationType = "";
            if(recommendReason == null) recommendReason = "";

            DressCollocationInfo dressCollocationInfo = new DressCollocationInfo(collocationUrl, collocationImgUrl, collocationType, recommendReason, false, false);
            try{
                infoMapper.insertSelective(dressCollocationInfo);

                List<DressCollocationItem> itemList = new ArrayList<>();
                JSONArray items = (JSONArray) data.get("dp");
                for(int index=0;index<items.size();index++){
                    JSONObject item = items.getJSONObject(index);
                    String itemUrl = (String)item.get("source_site_url");
                    String itemName = (String)item.get("title");
                    String brandId = (String) item.get("bid");
                    String itemBrand = brandMap.get(brandId);
                    String itemImgUrl = SysConstant.AJAX_BASE_URL + (String) item.get("img_url");
                    BigDecimal price = BigDecimal.valueOf(Double.valueOf((String) item.get("price")));

                    if(itemUrl == null) itemUrl = "";
                    if(itemName == null) itemName = "";
                    if(itemBrand==null) itemBrand = "";
                    if(itemImgUrl == null) itemImgUrl = "";
                    if(price == null) price = BigDecimal.ZERO;

                    DressCollocationItem dressCollocationItem = new DressCollocationItem(dressCollocationInfo.getId(), itemUrl, itemName, itemBrand, itemImgUrl, price, false);
                    itemList.add(dressCollocationItem);
                }
                itemMapper.insertBatch(itemList);
            }catch(Exception e){
                logger.error("爬取插入有误,名字为："+dressCollocationInfo.getCollocationType());
                logger.error(e.getMessage());
                continue;
            }
        }
    }
}
