package com.lhr.frame;

 public class ServiceConfig {
     //?c=api&a=getList&page_id=0
     private static final int  SERVICE_TYPE=1;
     public static String BASEURL="";

     static {
         switch (SERVICE_TYPE){
             case 1:
                 BASEURL="http://static.owspace.com/";
                 break;
             case 2:
                 break;
             case 3:
                 break;
         }
     }

}
