package club.vtuka.tuka.controller;

/**
 * Created by Vicky on 2018/12/2.
 */

//@ResponseBody
//@RequestMapping("/dress")
public class ImageController {
    String imagePath = "/data/res/image/";

//    @RequestMapping(value="/image",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String getImageUrl(@Param("imageNO") Integer imageNO){
//        JSONObject result = new JSONObject();
//        result.put("path", imagePath+);
//
//        return result.toJSONString();
//    }

    public static void main(String args[]){
            int x=123;
            String x_str = String.valueOf(x);

        System.out.println(x_str);
            StringBuilder temp = new StringBuilder();
            int length = x_str.length();
            for(int i=0;i<length;i++){
                if(x<0 && i==length){
                    break;
                }
                System.out.println(length-i);
                int k=Integer.parseInt(String.valueOf(x_str.charAt(length-i-1)));
                temp.append(x_str.charAt(k));
            }
            int reversed = Integer.parseInt(temp.toString());
            if(x<0){
                System.out.println(0 - reversed);
            }
            if(reversed > 230 || reversed<-231){
                System.out.println(0);
            }

    }
}
