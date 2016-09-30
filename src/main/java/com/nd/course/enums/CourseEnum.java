package com.nd.course.enums;

/**
 * Created by newtonk on 2016/8/18 0018.
 */
public enum CourseEnum {
    CN("CN","语文"),MA("MA","数学"),EN("EN","英语"),
//    HI("HI","历史"),GE("GE","地理"),PO("PO","思想品德"),BI("BI","生物"),PH("物理","物理"),CH("CH","化学")
    ;

    private String code;
    private String name;

    CourseEnum(String code,String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public String getName(){return name;}

    public static boolean isContain(String courseCode){
        for (CourseEnum courseEnum : CourseEnum.values()) {
            if(courseEnum.getCode().equals(courseCode)){
                return true;
            }
        }
        return false;
    }

    public static CourseEnum getEnum(String courseCode){
        for (CourseEnum courseEnum : CourseEnum.values()) {
            if (courseEnum.getCode().equals(courseCode)) {
                return courseEnum;
            }
        }
        return null;
    }
}
