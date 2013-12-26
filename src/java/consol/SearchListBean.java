package consol;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="searchListBean")
public class SearchListBean implements java.io.Serializable {
    
    private java.util.List<SearchTableField> aSearch = new java.util.ArrayList<SearchTableField>();
    public java.util.List<SearchTableField> getSearched() {return aSearch;}

            
    private final String[] CAPTION = {"Memo Order", "Communication", "Endorsement", "Ordinance", "Resolution", "Minutes", "Service Record"};
    private final String[] DBTABLE = {"memoranda",  "communication", "endorsement", "ordinance", "resolution"}; //, "minutas", "serbisyo"};

    public String getCaption() {return CAPTION[main.Resources.getDocument()];}
    public String getTitulo() {return main.Resources.getTitulo();}
    
    public SearchListBean() {
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        
        String titulo    = main.Resources.getTitulo();
        Short  document  = main.Resources.getDocument(),
               katuig    = main.Resources.getKatuig();
        Boolean isItYear = main.Resources.getTitulo().matches("[+-]?\\d*(\\.\\d+)?");

        
        String query;
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            switch (document) {
                case 0:
                case 1:
                case 2:
                    query = "SELECT " +
                                DBTABLE[document] + ".series, " +
                                DBTABLE[document] + ".numbers, " +
                                DBTABLE[document] + ".titles, " +
                                "classify.class_name, " +
                                DBTABLE[document] + ".types, " +
                                "' ', " +
                                DBTABLE[document] + ".document " +
                            "FROM " + 
                                "legis." + DBTABLE[document] + " JOIN legis.classify " +
                                "ON " + DBTABLE[document] + ".class = classify.classid " +
                            "WHERE " +
                                "(" + (isItYear ? DBTABLE[document] + ".series = " + titulo : DBTABLE[document] + ".titles LIKE '%" + titulo + "%'") + ") " +
                            "ORDER BY " +
                                DBTABLE[document] + ".numbers";
                    break;

                case 3:
                case 4:
                    query = "SELECT " +
                                DBTABLE[document] + ".series, " +
                                DBTABLE[document] + ".numbers, " +
                                DBTABLE[document] + ".titles, " +
                                "classify.class_name, " +
                                DBTABLE[document] + ".types, " +
                                "humane(authors.lname, authors.fname, authors.mname) " +
                                DBTABLE[document] + ".document " +
                            "FROM " + 
                                "legis." + DBTABLE[document] + " JOIN legis.classify " +
                                "ON " + DBTABLE[document] + ".class = classify.classid " +
                                "JOIN legis.authors " +
                                "ON " + DBTABLE[document] + ".author = authors.authid " +
                            "WHERE " +
                                "(" + (isItYear ? DBTABLE[document] + ".series = " + titulo : DBTABLE[document] + ".titles LIKE '%" + titulo + "%'") + ")" +
                            "ORDER BY " +
                                DBTABLE[document] + ".numbers";
                    break;
                    
                case 5:
                    query = "SELECT " +
                                "minutas.series, " +
                                "minutas.numbers, " +
                                "minutas.titles, " +
                                "classify.class_name, " +
                                "minutas.types, " +
                                "humane(authors.lname, authors.fname, authors.mname) " +
                                "minutas.document " +
                            "FROM " + 
                                "legis.minutas JOIN legis.classify " +
                                "ON minutas.class = classify.classid " +
                                "JOIN legis.authors " +
                                "ON minutas.author = authors.authid " +
                            "WHERE " +
                                "(minutas.titles LIKE '%" + titulo + "%') AND " +
                                "(DATE_PART('YEAR', minutas.petsa) = " + katuig + ") " +
                            "ORDER BY " +
                                "minutas.petsa";
                    break;
                    
                default:
                    query = "SELECT " +
                                "serbisyo.series, " +
                                "serbisyo.numbers, " +
                                "serbisyo.titles, " +
                                "classify.class_name, " +
                                "serbisyo.types, " +
                                "humane(authors.lname, authors.fname, authors.mname) " +
                                "serbisyo.document " +
                            "FROM " + 
                                "legis.serbisyo JOIN legis.classify " +
                                "ON serbisyo.class = classify.classid " +
                                "JOIN legis.authors " +
                                "ON serbisyo.author = authors.authid " +
                            "WHERE " +
                                "(serbisyo.titles LIKE '%" + titulo + "%')" +
                            "ORDER BY " +
                                "serbisyo.numbers";
            }
            
            
            aSearch.clear();
            java.sql.ResultSet rst = jdbc.executeQuery(query);
            while (rst.next())
                aSearch.add(new SearchTableField(rst.getShort(1), rst.getShort(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getLong(7)));
            rst.close();

            
        } catch (Exception ex) {
            context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));
        } finally {
            if (jdbc != null) try {
                jdbc.close();
            } catch (Exception ex) {
                context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));
            }
        }
    }

}
