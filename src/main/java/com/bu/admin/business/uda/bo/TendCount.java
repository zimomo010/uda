package com.bu.admin.business.uda.bo;

import lombok.Data;

@Data
public class TendCount {
        private Long dayDiff;
        private Long count;

        public TendCount(Long dayDiff, Long count) {
            this.dayDiff = dayDiff;
            this.count = count;
        }

}
