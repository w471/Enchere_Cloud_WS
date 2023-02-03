package group.enchere.ModelMongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document("commentaire")
public class Commentaire {
    String appreciation;
    String commentaire;
    double note;
    String[] clientInfos;

    public String[] getClientInfos() {
        return clientInfos;
    }

    public void setClientInfos(String[] clientInfos) {
        this.clientInfos = clientInfos;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "appreciation='" + appreciation + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", note=" + note +
                ", clientInfos=" + Arrays.toString(clientInfos) +
                '}';
    }
}
