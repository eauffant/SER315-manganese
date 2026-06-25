public class Result {

    String resultId;
    int placement;

    public Result(String resultId, int placement) {
        this.resultId = resultId;
        this.placement = placement;
    }

    public boolean isPodium() {
        return this.placement <= 3;
    }

    public String getResultId() {
        return this.resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public int getPlacement() {
        return this.placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

}
