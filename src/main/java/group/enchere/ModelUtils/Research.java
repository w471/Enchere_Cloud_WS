package group.enchere.ModelUtils;

import group.enchere.CustomAnnotation.SearchField;
import group.enchere.model.EnchereStatus;
import org.springframework.jdbc.core.JdbcTemplate;

//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
//import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.List;

public class Research {
    @SearchField
    Integer idCategorie;
    String idCategorieSign;
    @SearchField(isQuoted = true)
    Timestamp timingStart;
    String timingStartSign;
    @SearchField(isQuoted = true)
    String description;
    String descriptionSign;
    @SearchField
    Integer status;
    String statusSign;

//    we are talking about the current highest price for one enchere
    @SearchField
    Double price;
    String priceSign;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusSign() {
        return statusSign;
    }

    public void setStatusSign(String statusSign) {
        this.statusSign = statusSign;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceSign() {
        return priceSign;
    }

    public void setPriceSign(String priceSign) {
        this.priceSign = priceSign;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIdCategorieSign() {
        return idCategorieSign;
    }

    public void setIdCategorieSign(String idCategorieSign) {
        this.idCategorieSign = idCategorieSign;
    }

    public Timestamp getTimingStart() {
        return timingStart;
    }

    public void setTimingStart(Timestamp timingStart) {
        this.timingStart = timingStart;
    }

    public String getTimingStartSign() {
        return timingStartSign;
    }

    public void setTimingStartSign(String timingStartSign) {
        this.timingStartSign = timingStartSign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionSign() {
        return descriptionSign;
    }

    public void setDescriptionSign(String descriptionSign) {
        this.descriptionSign = descriptionSign;
    }

    @Override
    public String toString() {
        return "Research{" +
                "idCategorie=" + idCategorie +
                ", idCategorieSign='" + idCategorieSign + '\'' +
                ", timingStart=" + timingStart +
                ", timingStartSign='" + timingStartSign + '\'' +
                ", description='" + description + '\'' +
                ", descriptionSign='" + descriptionSign + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }


    public String prepareResearch(){
//            the value and the sign you should use are passed , if they don't exist use the default
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM V_ENCHERE_STATUS WHERE TRUE");
        Field[] allFields = this.getClass().getDeclaredFields();
        Object value = null;
        for (int i =0;i<allFields.length;i++) {
            if(allFields[i].isAnnotationPresent(SearchField.class)) {
                stringBuilder.append(" and "+allFields[i].getName()+" ");
                try {
//                    PropertyDescriptor propertyDescriptor = null;
//                    propertyDescriptor = new PropertyDescriptor(allFields[i].getName(), this.getClass());
                    allFields[i].setAccessible(true);


//                    Method m = propertyDescriptor.getReadMethod();
                    value = allFields[i].get(this);

                        System.out.println("for field "+allFields[i].getName()+"="+value);
                    if ( value == null)
                        stringBuilder.append(allFields[i].getAnnotation(SearchField.class).defaultSign()+" "+allFields[i].getAnnotation(SearchField.class).defaultValue());
                    else{
                        if(allFields[i].getAnnotation(SearchField.class).isQuoted())
                        stringBuilder.append(this.getClass().getDeclaredField(allFields[i].getName()+"Sign").get(this)+" '%"+value+"%'");

                        else
                            stringBuilder.append(this.getClass().getDeclaredField(allFields[i].getName()+"Sign").get(this)+" "+value+"");
                    }


                }catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }

//                    System.out.println("Default value being "+dv[i]+" and sign "+s[i]);
            }

        }

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public ArrayList<EnchereStatus> executeSearch(String query, JdbcTemplate jdbcTemplate){
        ArrayList<EnchereStatus> correspondEnchere = new ArrayList<>();
        jdbcTemplate.query(query,(rs, rowNum) -> new EnchereStatus(
                rs.getInt("idenchere"),
                rs.getTimestamp("timingStart").toLocalDateTime(),
                rs.getInt("idLauncher"),
                rs.getString("description"),
                rs.getInt("idCategorie"),
                rs.getDouble("startPrice"),
                rs.getInt("duration"),
                rs.getDouble("commission"),
                rs.getInt("status"),
                rs.getDouble("price"),
                rs.getString("image")
                )
        ).forEach(enchere-> correspondEnchere.add(enchere));

        return correspondEnchere;
    }
}
