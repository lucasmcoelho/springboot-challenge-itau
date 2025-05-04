package br.com.lucasmcoelho.chalengeitauspring.dto;

import java.util.DoubleSummaryStatistics;

public class StatisticsDTO {

    private long count;

    private double sum;

    private double avg;

    private double min;

    private double max;

    public StatisticsDTO(DoubleSummaryStatistics stats) {

        this.count = stats.getCount();
        this.sum = stats.getSum();
        this.avg = stats.getAverage();
        this.min = Math.min(0, stats.getMin());
        this.max = Math.max(0,stats.getMax());
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
