@api
Feature: US001 Create User
  @api01
  Scenario: TC01 Database de basarili bir sekilde user olusturabilmeliyim
    Given base URL "https://reqres.in" ve path parametresi "/api/users" kullanilir
    And name "ali" ve job "qa" ile payload olusturulur
    When post request gönderilir ve response alinir
    Then status code 201 olmalidir
    And response content type "application/json; charset=utf-8" olmalidir
    And response name "ali" ve job "qa" olmalidir