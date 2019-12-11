package KeywordDrivenTestFramework.Entities;

/**
 * Created with IntelliJ IDEA. User: fnell Date: 2013/04/26 Time: 3:20 PM To
 * change this template use File | Settings | File Templates.
 */
public class Enums {

    public enum BrowserType {

        none, IE, FireFox, Chrome, Safari, mobileSafari, mobileChrome, PhantomJs, EDGE;
    }

    public enum ResultStatus {

        PASS, FAIL, WARNING, UNCERTAIN
    }

    public enum RelativePosition {

        Above, Below, Right, Left
    }
    public enum TestLogResult {

        PASSED, FAILED, INCOMPLETE, TODO
    }
    
    public enum ExecutionMethod {

        AUTOMATE, MANUAL
    }
   
    
    public enum MobilePlatform {
        Android, iOS
    }
    
    public enum Device 
    {
       Emulator("udid", "emulator-5554", "http://127.0.0.1:4722/wd/hub", MobilePlatform.Android),
       geny_motion_Tablet("udid", "192.168.188.101:5555", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       Huawei("udid", "FUH7N16928004797", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
       iPhone_4_SE("udid","d419f3fbaeedaa7b3f0959f2bae016711a3e39a9","http://localhost:8889", MobilePlatform.iOS),
       iPhone_4_1("udid", "FC701A3EE383A8A98CDFC3FEB17AB3D55ADB6251", "http://10.0.1.97:4724/wd/hub", MobilePlatform.iOS),
       iPhone_4_2("udid", "069750BDDE450E9E1A8483347C2FD1B8CAABAC0C", "http://10.0.1.97:4726/wd/hub", MobilePlatform.iOS),
       iPhone_6("udid","871bcf2677f6c0460be47b70353cc91c1b476510","http://localhost:8889", MobilePlatform.iOS),
       iPhone_7("udid","ad38fd9c4354dc6b917e106175da10e7bbc936f0","http://localhost:8889", MobilePlatform.iOS),
       Nexus_7("udid", "0921880b", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       Nexus_5("udid", "074514c9d007b8f8", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       PowerTablet("udid", "0123456789ABCDEF", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
       Samsung_Tablet("udid", "52004947bca12100", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       Samsung_Tablet_2("udid", "4572a1631dd5857e", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       Samsung_Note_2_Tablet("udid", "c3206a701e5aa11", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
       Samsung_Note_2("udid", "4df090ae6af88fc1", "http://127.0.0.1:4491/wd/hub", MobilePlatform.Android),
       SamSung_S3("udid", "4df0b16c57519fa5", "http://127.0.0.1:4492/wd/hub", MobilePlatform.Android),
       SamSung_S3_JP("udid", "4df0ec687dfeafd5", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
       SamSung_S3_J6("udid", "4200450c4fc43400", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
       SamSung_S4("udid", "4d00cb02c5ce4001", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
       SamSung_neo("udid", "1101fa465595724a", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
       SamSung_S6("udid", "0816081d9fc60505", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
       SamSung_S7_edge("udid", "ad08160338b35f4a41", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
       SamSung_S4_Rio("udid", "4d00168240c03049", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        SamSung_Custom_Phone("udid", "emulator-5554", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        Techno_C9("udid", "01H535X671102995", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android);
       
       public final String CapabilityName;
       public final String DeviceID;
       public final String ServerURL;
       public final MobilePlatform platform;
        
       // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will 
       // define the order in which the enum types' properties are specified. 
       Device(String CapabilityName, String DeviceID, String ServerURL, MobilePlatform _platform)
       {
           this.CapabilityName = CapabilityName;
           this.DeviceID = DeviceID;
           this.ServerURL = ServerURL;
           this.platform = _platform;
       }
      
    }
    
    public enum DeviceConfig {
        
        //Capability information is stored here.
        //AppName and AppFilePath are for both APK and IPA.
        //Uses the format Name(deviceName, platformName, automationName, Version, appPackage, appActivity, appName, appFilePath)
        
        Santam("5554", "Android", "Appium", "5.1", "com.experitest.ExperiBank", ".LoginActivity", "Santam_1.3.1.apk", System.getProperty("user.dir")+"\\Applications"),
        Santam_iOS("871bcf2677f6c0460be47b70353cc91c1b476510","iOS","XCUITest","10.2.1","za.co.santam.mobile.sos","za.co.santam.mobile.sos.LoginActivity","SantamSOS_2.0.0.ipa",System.getProperty("user.dir")+"/Applications"),
        TestRoboNedbank("4200450c4fc43400","Android","Appium","5.1","","","",System.getProperty("user.dir")+"\\Applications"),
        Test("deviceName", "platformName", "automationName", "Version", "appPackage", "appActivity", "ApplicationName", "ApplicationFIlePath");
        
        public final String deviceName;
        public final String platformName;
        public final String automationName;
        public final String Version;
        public final String appPackage;
        public final String appActivity;
        public final String ApplicationName;
        public final String ApplicationFilePath;
       
        
        // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will 
       // define the order in which the enum types' properties are specified. 
        
        DeviceConfig(String deviceName, String platformName, String automationName, String Version, String appPackage, String appActivity, String ApplicationName, String ApplicationFilePath)
        {
           this.deviceName = deviceName; 
           this.platformName = platformName; 
           this.automationName = automationName; 
           this.Version = Version; 
           this.appPackage = appPackage; 
           this.appActivity = appActivity; 
           this.ApplicationName = ApplicationName;
           this.ApplicationFilePath = ApplicationFilePath;
        }
    }

    public enum TestRailConfig
    {
        ThirdbridgeTestRail("http://localhost/", "nkelechi@dvt.co.za", "123QWEasd");
        public final String username;
        public final String baseUrl;
        public final String password;

        TestRailConfig(String baseUrl, String username, String password){
            this.baseUrl = baseUrl;
            this.username = username;
            this.password = password;
        }
    }

    public enum Database
    {
        // Set Database Connection Information Here. 
        Example("org.apache.derby.jdbc.EmbeddedDriver","jdbc:derby://localhost:1527/sample","app","app");
        
        public final String Driver;
        public final String ConnectionString;
        public final String username;
        public final String password;
        
        Database(String Driver, String ConnectionString, String username, String password)
        {
            this.Driver = Driver;
            this.ConnectionString = ConnectionString;
            this.username = username;
            this.password = password;
        }
                
    }

    public enum Environment {
        // Add environment urls here, parameter order is defined by the constructor (Environment) definition below
        // Please note that adding an addtional environment type will require you to comma-seperate them.
        // Visit http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html to learn more about Java Enum declarations. 

        // Here we are declaring the Dev Environment type, and passing the following two properties, a url and a connection string, 
        // which are defined below as both being string literals:
        // DEV[FirstPageURL,FirstDatabaseConnectionString]
// Add environment urls here, parameter order is defined by the constructor (Environment) definition below
        // Please note that adding an addtional environment type will require you to comma-seperate them.
        // Visit http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html to learn more about Java Enum declarations. 

        // Here we are declaring the Dev Environment type, and passing the following two properties, a url and a connection string, 
        // which are defined below as both being string literals:
        // DEV[FirstPageURL,FirstDatabaseConnectionString]
        AMAZON("https://www.amazon.com/","https://10.74.10.10//"),
        QA("https://www.amazon.com/","https://10.74.10.10//"),
        EasiPlus_Quote("https://www.amazon.com/","https://10.74.10.10//"),
        EasiPlus_Quote_DEV("https://new-public-web.demo.nonprod.my.oldmutual.co.za/easiquote","https://10.74.10.10//"),
        CMB_DEV("https://new-public-web.demo.nonprod.my.oldmutual.co.za/call-me-back-test","https://10.74.10.10//"),
        EasiPlus_Quote_PreDEV("http://localhost:3000/easiquote","https://10.74.10.10//"),
        EasiPlus_App("https://new-public-web-qa.nonprod.digitalplatform.oldmutual.co.za/easiquote","https://10.74.10.10//");
        
        
        
       
   
        // For each system (website1, database1, website2 etc.) within the defined environment (Dev, QA, Prod etc.)
        // you will have to declare the appropriate string to store its properties (URL or connection string etc.).
        public final String IPURL;
        public final String SiteURL;
//        public final String PageUrlSantam;

//        public final String ForgotPasswordURL;
        // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will
        // define the order in which the enum types' properties are specified.
        Environment( String _AmazonURL , String _IPURL) 
        {
            this.IPURL = _IPURL;
            this.SiteURL = _AmazonURL;
        }

    }
    
    public static Device resolveDevice (String device)
    {
        switch (device.toUpperCase()) {
            case "EMULATOR":
                return  Device.Emulator;
            case "SAMSUNG_TABLET":
                return  Device.Samsung_Tablet;
            case "SAMSUNG_CUSTOM_PHONE":
                return Device.SamSung_Custom_Phone;
            case "NEXUS_5":
                return Device.Nexus_5;
             case "IPHONE_7":
                return Device.iPhone_7;
            default:
                return null;
        }
    }
    
    public static DeviceConfig resolveDeviceConfig (String deviceConfig)
    {
        switch (deviceConfig.toUpperCase()) {
            case "SANTAM":
                return  DeviceConfig.Santam;
            case "TEST":
                return DeviceConfig.Test;
            default:
                return null;
        }
    }
    
     public static MobilePlatform resolveDMobilePlatform (String mobilePlatform)
    {
        switch (mobilePlatform.toUpperCase()) {
            case "ANDROID":
                return  MobilePlatform.Android;
            case "IOS":
                return MobilePlatform.iOS;
            default:
                return null;
        }
    }
    
//    public static DeviceConfig resolveDeviceConfig(String deviceConfig)
//    {
//        switch (deviceConfig.toUpperCase()) {
//            case "HOLLARDANDROID":
//                return DeviceConfig.HollardAndroid;
//            
//            default:
//                return null;
//        }
//    }
    
    public static Environment resolveTestEnvironment(String environment) {
        switch (environment.toUpperCase()) {
            case "QA":
                return Environment.QA;
            case "EasiPlus_Quote":
                return Environment.EasiPlus_Quote;
                
            
            default:
                return null;
        }
    }
    
    
    public static BrowserType resolveBrowserType(String browserType) 
    {

        switch (browserType) {
            case "IE":
                return BrowserType.IE;
            case "FireFox":
                return BrowserType.FireFox;
            case "Chrome":
                return BrowserType.Chrome;
            case "Safari":
                return BrowserType.Safari;
            default:
                return BrowserType.IE;
        }
    }
}
