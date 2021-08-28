package com.xzy.utils;


public class GlobalConstant {

    /**
     * @description 默认密码
    */
    public static final String DEFAULT_PASSWD = "123";


    /**
     * @description 分页中每页显示大小
    */
    public static final Integer DEFAULT_PAGESIZE = 5;

    /**
     * @description 内部错误
    */
    public static String INNER_ERROR = "内部错误";

    /**
     * @description 角色枚举
    */
    public static enum ROle_Type {
        /**
         * 管理员用户
         */
        ADMIN("admin",1),
        /**
         * 老师用户
         */
        TEACHER("teacher",2),

        /**
         * 学生用户
         */
        STUDENT("student",3);

        private String name;
        private int index;

        private ROle_Type(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static String getName(int index) {
            for (ROle_Type rOleType : ROle_Type.values()) {
                if (rOleType.getIndex() == index) {
                    return rOleType.name;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
