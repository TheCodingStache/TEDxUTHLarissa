package thecodingstache.tedxuthlarissa.Model;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.RequestBuilder;

public class Speakers {
    private String name;
    private String occupation;
    private int image;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Speakers(String name, String occupation, int image, String title) {
        this.name = name;
        this.occupation = occupation;
        this.image = image;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Speakers(String achilleas_gravanis, String occupation, RequestBuilder<Drawable> load, String στους_εξι_δρόμους) {

    }

//    public static String[] name = new String[]{
//            "Achilleas Gravanis",
//            "Fotis Filippopoulos",
//            "Marina Hatsopoulos",
//            "Conor McGann",
//            "Constantine Venetopoulos",
//            "Eleftherios Beltsios",
//            "Lucy Xu",
//            "Valentina Kordi",
//            "Eleni Matraki",
//            "Orestis Omran",
//            "Christos Tsagkaris",
//            "Vassilis Gerogiannis",
//            "Zanettos Louka",
//            "Dimitris Koutsioulis",
//            "Konstantinos Giamalis"
//    };
//    public static String[] occupation = new String[]{
//            "Professor of Pharmacology Faculty of Medicine University of Crete / Collaborative Researcher IMBB-FORTH",
//            "Corporate Innovation Specialist",
//            "Board Chair, Levitronix Technologies",
//            "Mobile UX & Retail Specialist at Google Ireland",
//            "Director",
//            "MD Candidate",
//            "Founder, The Port",
//            "Mindset & High-Performance Coach for Executives, Entrepreneurs and Teams",
//            "Lawyer/ Accredited Life, Wellness & Business Coach",
//            "Counsel Head of EU-Greek Practice",
//            "MD Candidate",
//            "Professor of UTH Faculty of Digital Systems",
//            "Editor/Owner of Intelligence Media",
//            "CEO EnzyQuest",
//            "CPO @ efood (Chief Product Officer)"
//    };
//    public static int[] photos = new int[]{
//            R.drawable.speakers
//};
}
