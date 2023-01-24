//package group.enchere.ModelUtils;
//
//import group.enchere.CustomAnnotation.SearchField;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.Date;
//import java.time.LocalDate;
//
//public class Research {
//    @SearchField
//    Integer categorie;
//    String categorieSign;
//    @SearchField
//    Date date;
//    String dateSign;
//    @SearchField
//    String keyword;
//    String keywordSign;
//
//    @SearchField
//    Double price;
//    String priceSign;
//    @SearchField
//    Integer statut;
//    String statutSign;
//
//    public String getKeywordSign() {
//        return keywordSign;
//    }
//
//    public void setKeywordSign(String keywordSign) {
//        this.keywordSign = keywordSign;
//    }
//
//    public String getDateSign() {
//        return dateSign;
//    }
//
//    public void setDateSign(String dateSign) {
//        this.dateSign = dateSign;
//    }
//
//    public String getCategorieSign() {
//        return categorieSign;
//    }
//
//    public void setCategorieSign(String categorieSign) {
//        this.categorieSign = categorieSign;
//    }
//
//    public String getPriceSign() {
//        return priceSign;
//    }
//
//    public void setPriceSign(String priceSign) {
//        this.priceSign = priceSign;
//    }
//
//    public String getStatutSign() {
//        return statutSign;
//    }
//
//    public void setStatutSign(String statutSign) {
//        this.statutSign = statutSign;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Integer getCategorie() {
//        return categorie;
//    }
//
//    public void setCategorie(Integer categorie) {
//        this.categorie = categorie;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Integer getStatut() {
////        -1 is the default value
////        if(statut==-1)
////            return not null
//
//        return statut;
//    }
//
//    public void setStatut(Integer statut) {
//        this.statut = statut;
//    }
//
//    @Override
//    public String toString() {
//        return "Research{" +
//                "keyword='" + keyword + '\'' +
//                ", date=" + date +
//                ", categorie=" + categorie +
//                ", price=" + price +
//                ", statut=" + statut +
//                '}';
//    }
//
//    public String theFunction(String[] s,Object[] dv){
//        return "SELECT * FROM table where true and timingstart "+s[0]+" "+dv[0]+" and description "+s[1]+" "+dv[1]+" and idCategorie "+s[2]+" "+dv[2];
//    }
//
//    public Object[][] prepareResearch(){
////            the value and the sign you should use are passed , if they don't exist use the default
//            Field[] allFields = this.getClass().getDeclaredFields();
//            String[] s = new String[allFields.length];
//            Object[] dv = new Object[allFields.length];
//            for (int i =0;i<allFields.length;i++) {
//                if(allFields[i].isAnnotationPresent(SearchField.class)) {
//                    if (allFields[i] == null) {
//                        s[i] = allFields[i].getAnnotation(SearchField.class).defaultSign();
//                        dv[i] = allFields[i].getAnnotation(SearchField.class).defaultValue();
//                    } else {
//                        PropertyDescriptor propertyDescriptor = null;
//                        try {
//                            propertyDescriptor = new PropertyDescriptor(allFields[i].getName(), this.getClass());
//                            Method m = propertyDescriptor.getReadMethod();
//                            s[i] = (String) this.getClass().getDeclaredField(allFields[i].getName()+"Sign").get(this);
//                            dv[i] = m.invoke(this);
//
//                        } catch (IntrospectionException e) {
//                            throw new RuntimeException(e);
//                        } catch (InvocationTargetException e) {
//                            throw new RuntimeException(e);
//                        } catch (IllegalAccessException e) {
//                            throw new RuntimeException(e);
//                        } catch (NoSuchFieldException e) {
//                            throw new RuntimeException(e);
//                        }
//
//                    }
//                }
//            }
//
//        Object[][] result = new Object[2][allFields.length];
//            result[0] = s;
//            result[1] = dv;
//        return result;
////
////
//    }
//}
