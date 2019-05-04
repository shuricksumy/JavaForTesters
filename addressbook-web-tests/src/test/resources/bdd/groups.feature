Feature: Groups

  Scenario Outline: Croup creation
    Given a set of groups
    When I create a new group with name <name>, header <header> and footer <footer>
    Then the new set groups is equal to the old set with the added group

    Examples:
      | name     | header   | footer     |
      | testName | testHead | testFooter |
      | nameXYZ  | Hed123   | Foot123    |