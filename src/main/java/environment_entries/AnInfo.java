package environment_entries;

//	Environment Entries
//-When you work with enterprise apps, there are some situations where parameters of your app change from one deployment
// to another (depending on the country you are deploying in, the version of the app, etc.).

//-The problem of hard-coding prameters is that you have to change your code, recompile it, and redeploy the component fe. for
// each country in which the currency changes. The other option is to access a DB each time you invoke a method. That’s wasting resources.

//-What you really want is to store these parameters somewhere you can change them at deployment time.
// The deployment descriptor is the perfect place to set these parameters.

//-The DD (ejb-jar.xml) might be optional in EJB 3.2, but its use is legitimate with env entries. 

//-Env entries are specified in the DD and are accessible via DI (or via JNDI).

//-They support the foll. Java types: String, Character, Byte, Short, Integer, Long, Boolean, Double,

//-Listing shows the ejb-jar.xml file of ItemEJB defining two entries:
// currencyEntry / String / Euros and a changeRateEntry / Float / 0.80.

//<ejb-jar xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
//  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee  http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd" version="3.2"> 
//    <enterprise-beans> 
//        <session> 
//            <ejb-name>ItemEJB</ejb-name> 
//            <env-entry> 
//                <env-entry-name>currencyEntry</env-entry-name> 
//                <env-entry-type>java.lang.String</env-entry-type> 
//                <env-entry-value>Euros</env-entry-value> 
//            </env-entry> 
//            <env-entry> 
//                <env-entry-name>changeRateEntry</env-entry-name> 
//                <env-entry-type>java.lang.Float</env-entry-type> 
//                <env-entry-value>0.80</env-entry-value> 
//            </env-entry> 
//        </session> 
//    </enterprise-beans> 
//</ejb-jar>

//-Now that the parameters of the app are externalized in the DD, ItemEJB can use DI to get the value of each env entry. 
// @Resource(name = "currencyEntry") injects the value of the currencyEntry into the currency attribute. Note that the datatypes
// of the environment entry and the injected variable must be compatible; otherwise, the container throws an exception.

//	@Stateless  public class ItemEJB { 
//
//	    @Resource(name = "currencyEntry") 
//	    private String currency; 
//
//	    @Resource(name = "changeRateEntry")  
//	    private Float changeRate; 
//
//	    public Item convertPrice(Item item) {
//	        item.setPrice(item.getPrice() * changeRate);
//	        item.setCurrency(currency);
//	        return item;
//	    }
//	}

//-Looking up in JNDI:
//
//	    private Float ratio; 
//	
//	    @PostConstruct
//	    public void init() {
//	        try {
//	            ratio = InitialContext.doLookup("java:comp/env/ratioEntry");
//	        } catch (NamingException e) {
//	            e.printStackTrace();
//	        }
//	    }


public class AnInfo {}
