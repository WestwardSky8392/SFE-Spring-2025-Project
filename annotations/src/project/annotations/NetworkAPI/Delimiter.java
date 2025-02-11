package annotations.src.project.annotations.NetworkAPI;

public class Delimiter {
    private RetriveData retriveData;
   public Delimiter(RetriveData retriveData){
       this.retriveData = retriveData;
   }
    public enum isValid {
        SUCCESS(true),
        FAILURE(false);
        private boolean success;
        private isValid(boolean success) {
            this.success = success;
        }
        public boolean success() {
            return success;
        }
    }
}
