package search;



/**
 *
 * @author Administrator
 */
@javax.faces.bean.ManagedBean(name = "searchListBean")
@javax.faces.bean.RequestScoped

public class SearchListBean implements java.io.Serializable {

    private java.util.List<SearchTableField> aSearch = new java.util.ArrayList<SearchTableField>();

    public java.util.List<SearchTableField> getSearched() {
        return aSearch;
    }
    private String[] CAPTION = {"Memo Order", "Communication", "Endorsement", "Ordinance", "Resolution", "Minutes", "Service Record"};
    private String[] DBTABLE = {"memoranda", "communication", "endorsement", "ordinance", "resolution"}; //, "minutas", "serbisyo"};

    //private SearchTableField selectedSearch;
    public String getCaption() {return CAPTION[main.Resources.getDocument()];}

    public String getTitulo() {
        return SearchHolder.getTitulo();
    }
     

    public SearchListBean() {
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();

        String titulo = SearchHolder.getTitulo();
        Short document = SearchHolder.getDocument(),
                katuig = SearchHolder.getKatuig();
        Boolean isItYear = (SearchHolder.getTitulo()) == null ? false : SearchHolder.getTitulo().matches("[+-]?\\d*(\\.\\d+)?");
        Boolean selectAll = SearchHolder.getDisplayAll();

        String query;
        database.DBPgConn jdbc = null;
        try {
            jdbc = new database.DBPgConn();
            switch (document) {
                case 0:
                case 1:
                case 2:
                    query = "SELECT "
                            + DBTABLE[document] + ".idkeyseq, "
                            + DBTABLE[document] + ".series, "
                            + DBTABLE[document] + ".numbers, "
                            + DBTABLE[document] + ".titles, "
                            + "classify.class_name, "
                            + DBTABLE[document] + ".types, "
                            + DBTABLE[document] + ".document "
                            + "FROM "
                            + "legis." + DBTABLE[document] + " JOIN legis.classify "
                            + "ON " + DBTABLE[document] + ".class = classify.classid ";
                    if (selectAll) {
                        query = query + "LIMIT 100 ";
                    } else {
                        query = query + "WHERE "
                                + "(" + (isItYear ? DBTABLE[document] + ".series = " + titulo : DBTABLE[document] + ".titles LIKE '%" + titulo + "%'") + ") "
                                + "ORDER BY "
                                + DBTABLE[document] + ".numbers";
                    }
                    break;

                case 3:                               
                case 4:
                    query = "SELECT "
                            + DBTABLE[document] + ".idkeyseq, "
                            + DBTABLE[document] + ".series, "
                            + DBTABLE[document] + ".numbers, "
                            + DBTABLE[document] + ".titles, "
                            + "classify.class_name, "
                            + DBTABLE[document] + ".types, "
                            + "humane(authors.lname, authors.fname, authors.mname),"
                            + DBTABLE[document] + ".document "
                            + "FROM "
                            + "legis." + DBTABLE[document] + " JOIN legis.classify "
                            + "ON " + DBTABLE[document] + ".class = classify.classid "
                            + "JOIN legis.authors "
                            + "ON " + DBTABLE[document] + ".author = authors.authid ";
                    if (selectAll) {
                        query = query +  "ORDER BY "
                                + DBTABLE[document] + ".series DESC," + DBTABLE[document] + ".numbers DESC "
                                + "LIMIT 100";
                    } else {
                        query = query + "WHERE "
                                + "(" + (isItYear ? DBTABLE[document] + ".series = " + titulo : DBTABLE[document] + ".titles LIKE '%" + titulo + "%'") + ")"
                                + "ORDER BY "
                                + DBTABLE[document] + ".series DESC," + DBTABLE[document] + ".numbers DESC";
                    }
                    break;

                case 5:
                    query = "SELECT "
                            + "minutas.series, "
                            + "minutas.numbers, "
                            + "minutas.titles, "
                            + "classify.class_name, "
                            + "minutas.types, "
                            + "humane(authors.lname, authors.fname, authors.mname), "
                            + "minutas.document "
                            + "FROM "
                            + "legis.minutas JOIN legis.classify "
                            + "ON minutas.class = classify.classid "
                            + "JOIN legis.authors "
                            + "ON minutas.author = authors.authid "
                            + "WHERE "
                            + "(minutas.titles LIKE '%" + titulo + "%') AND "
                            + "(DATE_PART('YEAR', minutas.petsa) = " + katuig + ") "
                            + "ORDER BY "
                            + "minutas.petsa";
                    break;

                default:
                    query = "SELECT "
                            + "serbisyo.series, "
                            + "serbisyo.numbers, "
                            + "serbisyo.titles, "
                            + "classify.class_name, "
                            + "serbisyo.types, "
                            + "humane(authors.lname, authors.fname, authors.mname) "
                            + "serbisyo.document "
                            + "FROM "
                            + "legis.serbisyo JOIN legis.classify "
                            + "ON serbisyo.class = classify.classid "
                            + "JOIN legis.authors "
                            + "ON serbisyo.author = authors.authid "
                            + "WHERE "
                            + "(serbisyo.titles LIKE '%" + titulo + "%')"
                            + "ORDER BY "
                            + "serbisyo.numbers";
            }
            aSearch.clear();
            java.sql.ResultSet rst = jdbc.executeQuery(query);
            while (rst.next()) {
                switch (document){
                    case 0:
                    case 1:
                    case 2:
                        aSearch.add(new SearchTableField(rst.getLong(1),rst.getShort(2), rst.getShort(3), rst.getString(4), rst.getString(5), rst.getString(6), "", rst.getLong(7)));
                        break;
                    case 3:
                    case 4:
                        aSearch.add(new SearchTableField(rst.getLong(1),rst.getShort(2), rst.getShort(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getLong(8)));
                        break;
                    case 5:    
                }
                
            }
            rst.close();
            
//            querying classify table
            query = "SELECT * FROM legis.classify";
            rst = jdbc.executeQuery(query);
            arrKlase.clear();
            while (rst.next()) {
                arrKlase.add(new SelectOneMenuField(rst.getInt(1), rst.getString(2)));
            }
            rst.close();    
            
//             querying authors table
            query = "SELECT authid,humane(authors.lname, authors.fname, authors.mname)as name FROM legis.authors";
            rst = jdbc.executeQuery(query);
            arrAuthor.clear();
            while (rst.next()) {
                arrAuthor.add(new SelectOneMenuField(rst.getInt("authid"), rst.getString("name")));
            }
            rst.close();


        } catch (Exception ex) {
            context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));         
          
        } finally {
            if (jdbc != null) {
                try {
                    jdbc.close();
                } catch (Exception ex) {
                    context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));
                }
            }
        }
    }
    
     public void onEdit(org.primefaces.event.RowEditEvent event) {
         SearchTableField record=(SearchTableField)event.getObject(); 
         Long IDseq=record.getIDseq();
         String title=record.getTitle();
         short numero=record.getNumber();
         short tuig=record.getSeries();
         int klazID=Integer.parseInt(record.getKlass());
         char level=record.getType().charAt(0);
         int authorID=Integer.parseInt((!record.getAuthor().equals("")?record.getAuthor():"0"));       
         
         
         for (int klas = 0; klas < arrKlase.size(); klas++) {
             if (Integer.parseInt(record.getKlass()) == arrKlase.get(klas).getID()) {
                 record.setKlass(arrKlase.get(klas).getName());
                 break;
             }
         }          
         
        
            for (int owtor = 0; owtor < arrAuthor.size(); owtor++) {
                if (Integer.parseInt((!record.getAuthor().equals("")?record.getAuthor():"0")) == arrAuthor.get(owtor).getID()) {
                    record.setAuthor(arrAuthor.get(owtor).getName());
                    break;
                }
            } 
         
         
         record.setType((level=='B'?"Barangay":"City"));       
         docs.EditFileController controller = new docs.EditFileController();       
         boolean test  =controller.editFileController(IDseq, tuig, numero, title, klazID,level,authorID);     
         if (test) {
             for (int xyz = 0; xyz < aSearch.size(); xyz++) {
                 if (aSearch.get(xyz).getIDseq() == IDseq) {
                     record.setNumber(aSearch.get(xyz).getTempNo());
                     record.setSeries(aSearch.get(xyz).getTempYear());
                     record.setAuthor(aSearch.get(xyz).getTempAuthor());
                     record.setKlass(aSearch.get(xyz).getTempKlass());
                     record.setTitle(aSearch.get(xyz).getTempTitle());
                     record.setType(aSearch.get(xyz).getTempType());
                     break;
                 }
             }
         }
         
    }  
      
    public void onCancel(org.primefaces.event.RowEditEvent event) {         
        javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage("Operation Cancelled", getCaption()+ " " +((SearchTableField) event.getObject()).getNumber().toString()+ "-" + ((SearchTableField) event.getObject()).getSeries().toString());   
        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);       
    }  
    
    
    private java.util.List<SelectOneMenuField> arrKlase = new java.util.ArrayList<SelectOneMenuField>();
    public java.util.List<SelectOneMenuField> getKlase() {
        return arrKlase;
    }
    
    private java.util.List<SelectOneMenuField> arrAuthor = new java.util.ArrayList<SelectOneMenuField>();
    public java.util.List<SelectOneMenuField> getAuthor() {
        return arrAuthor;
    }
   
   public void removeItem(SearchTableField item) {
       aSearch.remove(item);
       Short document=SearchHolder.getDocument();
       new docs.DeleteFileController().delete(item.getIDseq(),document);
   }
}
