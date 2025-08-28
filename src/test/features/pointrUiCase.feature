Feature: Validate Blog Articles and find top 5 words


  Scenario: Extract top 5 repeated words from latest 3 articles

    Given Open the https://www.pointr.tech/blog URL
    Then I see blogPage page
    Then I find all articles on the page
    Then I verify that all articles are loaded

    #First Article
    And I scroll vertically by 1000 pixels
    And I wait readmore1 element 5 seconds at index 1
    And I click element: readMore1 at index 1
    #And I extract the top 5 repeated words from the article
    #And I go back

    #Second Article
    #And I click element: readMore at index 1
    #And I extract the top 5 repeated words from the article
    #And I go back

    #Third Article
    #And I click element: readMore at index 1
    #And I extract the top 5 repeated words from the article

    #.txt File Creation
    #And I create .txt file