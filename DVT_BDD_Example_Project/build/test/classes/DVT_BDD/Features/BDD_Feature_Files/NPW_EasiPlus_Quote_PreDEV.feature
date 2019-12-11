Feature: EasiPlus Quote

  Scenario Outline: 1- Positive Validate EasiPlus Quote
    Given I have navigated to EasiPlus Quote PreDEV
     When EasiPlus Quote PreDEV Page loads
     Then click Select Cover Me and My Direct Family
     When Who do you want to cover Header Loads
     Then select Myself from the Select Who You Want to Cover dropdown
     #When entering an <InvalidAge>
     #Then the Premium amount remains "R0" and an Error message is displayed
     When entering a <ValidAge> and  selecting the <CoverAmount> from the slider
     Then <Premium> amount is calculated
     When clicking Add To Cover
     Then <Premium> is added to Quote Summary
     When clicking the Dropdown icon
     Then the Quote Summary is minimized or maximized
  
    Examples: 
      | ValidAge | CoverAmount     | Premium  | InvalidAge |
      | "27"     | "R25000"        | "R 80"   | "12"       | 