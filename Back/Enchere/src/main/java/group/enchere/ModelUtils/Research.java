package group.enchere.ModelUtils;

import group.enchere.CustomAnnotation.SearchField;

import java.sql.Date;

public class Research {
    @SearchField
    String keyword;
    @SearchField
    Date date;
    @SearchField
    Integer categorie;
    @SearchField
    double price;
    @SearchField
    int statut;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatut() {
//        -1 is the default value
//        if(statut==-1)
//            return not null

        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Research{" +
                "keyword='" + keyword + '\'' +
                ", date=" + date +
                ", categorie=" + categorie +
                ", price=" + price +
                ", statut=" + statut +
                '}';
    }

    public String theFunction(String s1,String dv1){
        return "SELECT * FROM table where timingstart "+s1+" "+dv1;
    }

    public  

    public void callFunction(){
        String s1 = this.date.getClass().getAnnotation(SearchField.class).defaultSign();
        String dv1 = this.categorie.getClass().getAnnotation(SearchField.class).defaultValue();

        System.out.println(theFunction(s1,dv1));
    }
}
