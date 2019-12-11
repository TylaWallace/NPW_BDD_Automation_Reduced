Feature: EasiPlus Quote

  Scenario Outline: 1- Navigate and Validate EasiPlus Quote
    Given I have navigated to EasiPlus Quote PreDEV
     When EasiPlus Quote PreDEV Page loads
     Then click Select Cover Me and My Direct Family
     When Who do you want to cover Header Loads
     Then select Myself from the Select Who You Want to Cover dropdown
     When entering a <ValidAge> and  selecting the <CoverAmount> from the slider
     Then <Premium> amount is calculated
     When clicking Add To Cover
     Then <SumPremium> is added to Quote Summary
     When clicking the Dropdown icon
     Then the Quote Summary is minimized or maximized
     When clicking the Buy Online button
     Then the User is redirected to the EasiPlus Application
     Then click continue button
     When EPA Personal Details Page Loads
     Then Select a Title <Title> for Personal Details
     Then enter FirstName <FirstName> for Personal Details
     Then enter Surname <Surname> for Personal Details
     Then enter ID <ID> for Personal Details
     Then enter ContactNo <ContactNo> for Personal Details
     Then enter Email <Email> for Personal Details
     Then click continue button 
     When EPA Residential Address Page Loads
     Then enter Street Number <StreetNo> for Residential Address
     Then enter Street Name <StreetName> for Residential Address
     Then enter Suburb <Suburb> for Residential Address
     Then enter City <City> for Residential Address
     Then enter PostalCode <PostalCode> for Residential Address
     Then click continue button
     When Family Member Details Page Load
     Then Add a Parent Dependant <ParentDependent> <Title> <DependentFirstName> <Surname> <DependentDOB>
     Then click continue button
     When EPA Beneficiary Details Page Loads
     Then Add a Beneficiary <BeneficiaryTitle> <BeneficiaryName> <Surname> <BeneficiaryDOB> <BeneficiaryContactNo> <BeneficiaryEmail> <BeneficiaryRelationship>
     Then click continue button
     When Beneficiary Split Page Loads
     Then click continue button
     When Payment Details Page Loads
     Then Add Payment Details <DebitOrderDate> <BankName> <AccountNumber> <AccountType> <BranchCode>
     Then click continue button
  
    Examples: 
      | ValidAge | CoverAmount     | Premium | InvalidAge  | SumPremium | Title  | FirstName    | Surname    | ID              | ContactNo    | Email                           | StreetNo | StreetName    | Suburb  | City        | PostalCode | ParentDependent | DependentFirstName | DependentDOB | BeneficiaryTitle | BeneficiaryName | BeneficiaryDOB | BeneficiaryContactNo | BeneficiaryEmail                      | BeneficiaryRelationship | DebitOrderDate | BankName | AccountNumber | AccountType      | BranchCode |
      | "27"     | "R25000"        | "R 80"   | "12"       | "R80"      | "Mrs"  | "Testy-Lee!" | "NpwA'uto" | "9206094800087" | "0711110011" | "TestyLeeIsATest@testemail.com" | "14"     | "Test Street" | "Bronx" | "Cape Town" | "7800"     | "Mother"        | "Mama-Testy!"      | "09/06/1974" | "Mr"             | "Testy-Ben!"    | "09/06/1998"   | "0744254418"         | "TestyBensTestEmail@testemailben.com" | "Brother"               | "1st"          | "FNB"    | "1234567890"  | "Cheque Account" | "254005"   |