package homework.the_sixth_week;

public class Student {
    String id;
    String name;
    String MathScore;
    String ChineseScore;
    String EnglishScore;

    public Student(){

    }

    public Student(String id,String name,String mathScore,String chineseScore,String englishScore){
        this.id = id;
        this.name = name;
        this.MathScore = mathScore;
        this.ChineseScore = chineseScore;
        this.EnglishScore = englishScore;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setChineseScore(String chineseScore) {
        this.ChineseScore = chineseScore;
    }

    public void setEnglishScore(String englishScore) {
        this.EnglishScore = englishScore;
    }

    public void setMathScore(String mathScore) {
        this.MathScore = mathScore;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getChineseScore() {
        return this.ChineseScore;
    }

    public String getMathScore(){
        return this.MathScore;
    }

    public String getEnglishScore() {
        return EnglishScore;
    }

    public void ToString(){
        System.out.println("id:" + this.id + " name:" + this.name);
    }
}
