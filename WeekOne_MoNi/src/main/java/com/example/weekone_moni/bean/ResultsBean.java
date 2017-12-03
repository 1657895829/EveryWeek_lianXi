package com.example.weekone_moni.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ResultsBean {
        /**
         * _id : 5a1ad043421aa90fe725366c
         * createdAt : 2017-11-26T22:31:31.363Z
         * desc : 11-26
         * publishedAt : 2017-11-30T13:11:10.665Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171126223118_jeCYtY_chayexiaoguo_apple_26_11_2017_22_30_59_409.jpeg
         * used : true
         * who : 代码家
         */
        @Id(autoincrement = true)
        @Property(nameInDb = "www")     //此注解意为，修改@Property注解下的属性在数据库中的列名
        private Long ddd;

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        @Generated(hash = 1407287793)
        public ResultsBean(Long ddd, String _id, String createdAt, String desc, String publishedAt, String source, String type,
                String url, boolean used, String who) {
            this.ddd = ddd;
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
        }

        @Generated(hash = 1822271928)
        public ResultsBean() {
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

    @Override
    public String toString() {
        return "ResultsBean{" +
                "ddd=" + ddd +
                ", _id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    public Long getDdd() {
        return this.ddd;
    }

    public void setDdd(Long ddd) {
        this.ddd = ddd;
    }

    public boolean getUsed() {
        return this.used;
    }
}