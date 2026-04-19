package com.bu.admin.business.uda.bo;

import lombok.Data;

@Data
public class PublishTimeCount {
        private Integer hour;
        private Long count;

        public PublishTimeCount(Integer hour, Long count) {
            this.hour = hour;
            this.count = count;
        }

}
