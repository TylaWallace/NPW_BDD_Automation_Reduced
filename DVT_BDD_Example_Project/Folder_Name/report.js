$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Unit Tests/Nedbank_ReportExample/ChooseAGoal.feature");
formatter.feature({
  "line": 1,
  "name": "Choose a Goal",
  "description": "",
  "id": "choose-a-goal",
  "keyword": "Feature"
});
formatter.before({
  "duration": 13480218,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Choose School Investment Goal as a New User",
  "description": "",
  "id": "choose-a-goal;choose-school-investment-goal-as-a-new-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I want to invest for \"School\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I am an \"Existing Nedbank\" User",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "I answer the Security Questions \"Successfully\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I answer the \"What Is your Child\u0027s Name\" Question with \"Josh\"",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "I answer the \"When are they going to start school or university\" Question with \"2018\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I answer the \"Do you know how much you need to save\" Question with \"No\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I answer the \"What school or university do you think Josh will be going to\" Question with \"Rondebosch\"",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I answer the \"Just to make sure, I got the right one. Select the right one\" Question with \"Rondebosch Boy\u0027s Preparatory School\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I answer the \"Would you like to use that amount\" Question with \"Yes\"",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I should be prompted \"work out a good emergency savings plan\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I should be prompted \"We can see this is about R34 000 for you.\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "What Is your Child\u0027s Name",
      "offset": 14
    },
    {
      "val": "Josh",
      "offset": 56
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "When are they going to start school or university",
      "offset": 14
    },
    {
      "val": "2018",
      "offset": 80
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Do you know how much you need to save",
      "offset": 14
    },
    {
      "val": "No",
      "offset": 68
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "What school or university do you think Josh will be going to",
      "offset": 14
    },
    {
      "val": "Rondebosch",
      "offset": 91
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Just to make sure, I got the right one. Select the right one",
      "offset": 14
    },
    {
      "val": "Rondebosch Boy\u0027s Preparatory School",
      "offset": 91
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Would you like to use that amount",
      "offset": 14
    },
    {
      "val": "Yes",
      "offset": 64
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_answer_the_Question_with(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "work out a good emergency savings plan",
      "offset": 22
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_should_be_prompted(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "We can see this is about R34 000 for you.",
      "offset": 22
    }
  ],
  "location": "Nedbank_Report_Example_steps.i_should_be_prompted(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 16042,
  "status": "passed"
});
});