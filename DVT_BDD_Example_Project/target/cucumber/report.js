$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Unit-Tests/DVT_BDD/Features/BDD_Feature_Files/URLTest.feature");
formatter.feature({
  "line": 1,
  "name": "URL Testing Feature",
  "description": "",
  "id": "url-testing-feature",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "1- Navigate to a URL and Validate all the links present",
  "description": "",
  "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "I have navigated to \"\u003cURL\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "get the list of the links present on the page.",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "click on each link and verify the landing page.",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;",
  "rows": [
    {
      "cells": [
        "URL"
      ],
      "line": 9,
      "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;;1"
    },
    {
      "cells": [
        "https://www.toolsqa.com/"
      ],
      "line": 10,
      "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;;2"
    },
    {
      "cells": [
        "https://www.phptravels.net/index.php"
      ],
      "line": 11,
      "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 6444519900,
  "status": "passed"
});
formatter.before({
  "duration": 819328500,
  "status": "passed"
});
formatter.before({
  "duration": 9036500,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "1- Navigate to a URL and Validate all the links present",
  "description": "",
  "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "I have navigated to \"https://www.toolsqa.com/\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "get the list of the links present on the page.",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "click on each link and verify the landing page.",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.toolsqa.com/",
      "offset": 21
    }
  ],
  "location": "URL_Step_Definitions.i_have_navigated_to(String)"
});
formatter.result({
  "duration": 4362468300,
  "status": "passed"
});
formatter.match({
  "location": "URL_Step_Definitions.get_the_list_of_the_links_present_on_the_page()"
});
formatter.result({
  "duration": 477844900,
  "status": "passed"
});
formatter.match({
  "location": "URL_Step_Definitions.click_on_each_link_and_verify_the_landing_page()"
});
formatter.result({
  "duration": 320923300,
  "error_message": "org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document\n  (Session info: chrome\u003d78.0.3904.108)\n  (Driver info: chromedriver\u003d2.33.506120 (e3e53437346286c0bc2d2dc9aa4915ba81d9023f),platform\u003dWindows NT 10.0.18362 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/stale_element_reference.html\nBuild info: version: \u00273.6.0\u0027, revision: \u00276fbf3ec767\u0027, time: \u00272017-09-27T16:15:26.402Z\u0027\nSystem info: host: \u0027GTC-100684\u0027, ip: \u0027192.168.18.142\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_231\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities [{mobileEmulationEnabled\u003dfalse, hasTouchScreen\u003dfalse, platform\u003dXP, acceptSslCerts\u003dtrue, webStorageEnabled\u003dtrue, browserName\u003dchrome, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, platformName\u003dXP, setWindowRect\u003dtrue, unexpectedAlertBehaviour\u003d, applicationCacheEnabled\u003dfalse, rotatable\u003dfalse, networkConnectionEnabled\u003dfalse, chrome\u003d{chromedriverVersion\u003d2.33.506120 (e3e53437346286c0bc2d2dc9aa4915ba81d9023f), userDataDir\u003dC:\\Users\\twallace\\AppData\\Local\\Temp\\scoped_dir8784_17138}, takesHeapSnapshot\u003dtrue, pageLoadStrategy\u003dnormal, unhandledPromptBehavior\u003d, databaseEnabled\u003dfalse, handlesAlerts\u003dtrue, version\u003d78.0.3904.108, browserConnectionEnabled\u003dfalse, nativeEvents\u003dtrue, locationContextEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue}]\nSession ID: 53d1e0bf60054ba37636789def2ca77c\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\r\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:82)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:45)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:164)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:586)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:279)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:83)\r\n\tat KeywordDrivenTestFramework.Testing.TestClasses.URL_Step_PreDEV.then_click_on_each_link_and_verify_the_landing_page(URL_Step_PreDEV.java:75)\r\n\tat DVT_BDD.BDD_Step_Definitions.URL_Step_Definitions.click_on_each_link_and_verify_the_landing_page(URL_Step_Definitions.java:77)\r\n\tat ✽.Then click on each link and verify the landing page.(Unit-Tests/DVT_BDD/Features/BDD_Feature_Files/URLTest.feature:6)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 696745800,
  "status": "passed"
});
formatter.after({
  "duration": 88100,
  "status": "passed"
});
formatter.after({
  "duration": 94700,
  "status": "passed"
});
formatter.before({
  "duration": 5459140900,
  "status": "passed"
});
formatter.before({
  "duration": 133400,
  "status": "passed"
});
formatter.before({
  "duration": 161100,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "1- Navigate to a URL and Validate all the links present",
  "description": "",
  "id": "url-testing-feature;1--navigate-to-a-url-and-validate-all-the-links-present;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "I have navigated to \"https://www.phptravels.net/index.php\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "get the list of the links present on the page.",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "click on each link and verify the landing page.",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.phptravels.net/index.php",
      "offset": 21
    }
  ],
  "location": "URL_Step_Definitions.i_have_navigated_to(String)"
});
formatter.result({
  "duration": 10888947700,
  "status": "passed"
});
formatter.match({
  "location": "URL_Step_Definitions.get_the_list_of_the_links_present_on_the_page()"
});
formatter.result({
  "duration": 738647100,
  "status": "passed"
});
formatter.match({
  "location": "URL_Step_Definitions.click_on_each_link_and_verify_the_landing_page()"
});
formatter.result({
  "duration": 329568900,
  "error_message": "org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document\n  (Session info: chrome\u003d78.0.3904.108)\n  (Driver info: chromedriver\u003d2.33.506120 (e3e53437346286c0bc2d2dc9aa4915ba81d9023f),platform\u003dWindows NT 10.0.18362 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/stale_element_reference.html\nBuild info: version: \u00273.6.0\u0027, revision: \u00276fbf3ec767\u0027, time: \u00272017-09-27T16:15:26.402Z\u0027\nSystem info: host: \u0027GTC-100684\u0027, ip: \u0027192.168.18.142\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_231\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities [{mobileEmulationEnabled\u003dfalse, hasTouchScreen\u003dfalse, platform\u003dXP, acceptSslCerts\u003dtrue, webStorageEnabled\u003dtrue, browserName\u003dchrome, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, platformName\u003dXP, setWindowRect\u003dtrue, unexpectedAlertBehaviour\u003d, applicationCacheEnabled\u003dfalse, rotatable\u003dfalse, networkConnectionEnabled\u003dfalse, chrome\u003d{chromedriverVersion\u003d2.33.506120 (e3e53437346286c0bc2d2dc9aa4915ba81d9023f), userDataDir\u003dC:\\Users\\twallace\\AppData\\Local\\Temp\\scoped_dir8740_9933}, takesHeapSnapshot\u003dtrue, pageLoadStrategy\u003dnormal, unhandledPromptBehavior\u003d, databaseEnabled\u003dfalse, handlesAlerts\u003dtrue, version\u003d78.0.3904.108, browserConnectionEnabled\u003dfalse, nativeEvents\u003dtrue, locationContextEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue}]\nSession ID: 4460476d4a7c850d4e1903c427f81ae0\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\r\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:82)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:45)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:164)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:586)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:279)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:83)\r\n\tat KeywordDrivenTestFramework.Testing.TestClasses.URL_Step_PreDEV.then_click_on_each_link_and_verify_the_landing_page(URL_Step_PreDEV.java:75)\r\n\tat DVT_BDD.BDD_Step_Definitions.URL_Step_Definitions.click_on_each_link_and_verify_the_landing_page(URL_Step_Definitions.java:77)\r\n\tat ✽.Then click on each link and verify the landing page.(Unit-Tests/DVT_BDD/Features/BDD_Feature_Files/URLTest.feature:6)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 719589200,
  "status": "passed"
});
formatter.after({
  "duration": 65700,
  "status": "passed"
});
formatter.after({
  "duration": 32900,
  "status": "passed"
});
});