package homework.the_sixth_week;

public class Student {
    String id;
    String name;
    int MathScore;
    int ChineseScore;
    int EnglishScore;

    public Student(){

    }

    public Student(String id,String name,int mathScore,int chineseScore,int englishScore){
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

    public void setChineseScore(int chineseScore) {
        this.ChineseScore = chineseScore;
    }

    public void setEnglishScore(int englishScore) {
        this.EnglishScore = englishScore;
    }

    public void setMathScore(int mathScore) {
        this.MathScore = mathScore;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getChineseScore() {
        return this.ChineseScore;
    }

    public int getMathScore(){
        return this.MathScore;
    }

    public int getEnglishScore() {
        return EnglishScore;
    }

    public void ToString(){
        System.out.println("id:" + this.id + " name:" + this.name);
    }
}
