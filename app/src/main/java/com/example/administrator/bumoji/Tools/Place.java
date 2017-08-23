package com.example.administrator.bumoji.Tools;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Place {

    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * count : 1
     * suggestion : {"keywords":[],"cities":[]}
     * districts : [{"citycode":"024","adcode":"210100","name":"沈阳市","center":"123.465035,41.677284","level":"city","districts":[]}]
     */

    private String status;
    private String info;
    private String infocode;
    private String count;
    private SuggestionBean suggestion;
    private List<DistrictsBean> districts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<DistrictsBean> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictsBean> districts) {
        this.districts = districts;
    }

    public static class SuggestionBean {
        private List<String> keywords;
        private List<String> cities;

        public List<String> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<String> keywords) {
            this.keywords = keywords;
        }

        public List<String> getCities() {
            return cities;
        }

        public void setCities(List<String> cities) {
            this.cities = cities;
        }
    }

    public static class DistrictsBean {
        /**
         * citycode : 024
         * adcode : 210100
         * name : 沈阳市
         * center : 123.465035,41.677284
         * level : city
         * districts : []
         */

        private String citycode;
        private String adcode;
        private String name;
        private String center;
        private String level;
        private List<?> districts;

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCenter() {
            return center;
        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<?> getDistricts() {
            return districts;
        }

        public void setDistricts(List<?> districts) {
            this.districts = districts;
        }
    }
}
