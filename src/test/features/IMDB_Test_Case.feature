Feature: IMDB_Test_Case


  Scenario: Menuden Bulunan Filmle Arama Cubugu Uzerinden Bulunan Film Ozellikleri Aynı Olmalidir.

    Given Open the https://www.imdb.com/ URL
    Then I see imdbMainPage page

#   The Circus

    And I wait MenuButton element 20 seconds at index 1
    When I click element: MenuButton at index 1
    And I wait Awards&EventsButton element 20 seconds at index 1
    And I wait OscarsButton element 20 seconds at index 1
    When I click element: OscarsButton at index 1
    Then I see OscarsPage page
    And I wait EventHistoryText element 20 seconds at index 1
    And I wait 1929Button element 20 seconds at index 1
    When I double click element: 1929Button at index 1
    Then I see 1929AwardsPage page
    And I wait HonoraryAwardText element 20 seconds at index 1
    And I wait TheCircusCharlesChaplinTitleButton element 20 seconds at index 1
    When I click element: TheCircusCharlesChaplinTitleButton at index 1
    Then I see TheCircusPage page
    Then I get and store DirectorOfTheCircus Director Text for The Circus at index 1
    Then I get and store WriterOfTheCircus Writer Text for The Circus at index 1
    Then I get and store StarsOfTheCircus Stars Text for The Circus at index 1
    And I wait IMDbHomeButton element 20 seconds at index 1
    When I click element: IMDbHomeButton at index 1
    Then I see imdbMainPage page
    And I wait SearchbarTextArea element 20 seconds at index 1
    Then I enter "The Circus" text to SearchbarTextArea at index 1
    And I wait TheCircusSearchbarButton element 20 seconds at index 1
    When I click element: TheCircusSearchbarButton at index 1
    Then I see TheCircusPage page
    Then I have to compare DirectorOfTheCircus_SearchbarVersion element with stored Director text for The Circus at index 1
    Then I have to compare WriterOfTheCircus_SearchbarVersion element with stored Writer text for The Circus at index 1
    Then I have to compare StarsOfTheCircus_SearchbarVersion element with stored Stars text for The Circus at index 1
    Given Open the https://www.imdb.com/title/tt0018773/?ref_=ev_nom URL
    Then I see TheCircusPhotosPage page
    Then I need to just wait
    Then I control the photo links that whether they are broken or not for The Circus at index 1
    And I wait IMDbHomeButton element 20 seconds at index 1
    When I click element: IMDbHomeButton at index 1
    Then I see imdbMainPage page

#   The Jazz Singer

    And I wait MenuButton element 20 seconds at index 1
    When I click element: MenuButton at index 1
    And I wait Awards&EventsButton element 20 seconds at index 1
    And I wait OscarsButton element 20 seconds at index 1
    When I click element: OscarsButton at index 1
    Then I see OscarsPage page
    And I wait EventHistoryText element 20 seconds at index 1
    And I wait 1929Button element 20 seconds at index 1
    When I double click element: 1929Button at index 1
    Then I see 1929AwardsPage page
    And I wait HonoraryAwardText element 20 seconds at index 1
    And I wait TheJazzSingerTitleButton element 20 seconds at index 1
    When I click element: TheJazzSingerTitleButton at index 1
    Then I see TheJazzSingerPage page
    Then I get and store DirectorOfTheJazzSinger Director Text for Jazz Singer at index 1
    Then I get and store WritersOfTheJazzSinger Writers Text for The Jazz Singer at index 1
    Then I get and store StarsOfTheJazzSinger Stars Text for The Jazz Singer at index 1
    And I wait IMDbHomeButton element 20 seconds at index 1
    When I click element: IMDbHomeButton at index 1
    Then I see imdbMainPage page
    And I wait SearchbarTextArea element 20 seconds at index 1
    Then I enter "The Jazz Singer" text to SearchbarTextArea at index 1
    And I wait TheJazzSingerSearchbarButton element 20 seconds at index 1
    When I click element: TheJazzSingerSearchbarButton at index 1
    Then I see TheJazzSingerPage page
    Then I have to compare DirectorOfTheJazzSinger_SearchbarVersion element with stored Director text for The Jazz Singer at index 1
    Then I have to compare WritersOfTheJazzSinger_SearchbarVersion element with stored Writers text for The Jazz Singer at index 1
    Then I have to compare StarsOfTheJazzSinger_SearchbarVersion element with stored Stars text for The Jazz Singer at index 1
    Given Open the https://www.imdb.com/title/tt0018037/mediaindex/?ref_=tt_mi_sm URL
    Then I see TheJazzSingerPhotosPage page
    Then I need to just wait
    Then I control the photo links that whether they are broken or not for The Jazz Singer at index 1
