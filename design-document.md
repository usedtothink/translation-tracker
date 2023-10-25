# Design Document

## Translation Tracker Design

## 1. Problem Statement

At present, translation case management solutions for freelance translators are virtually non-existent. My product will 
address common translator needs by storing details for individual translation cases, estimating the time and cost for 
prospective cases, tracking upcoming deadlines, and providing a variety of interesting statistics that will help the 
translator to improve productivity.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that 
you might like help working through._

1. I would like to soft-delete clients and translation cases - would I still use DELETE in the endpoint? 
2.
3.

## 3. Use Cases

#### Translation Cases

U1. As a customer, I want to be able to view details about a translation case in a separate webpage.

U2. As a customer, I want to be able to create a new translation case and either associate it with an existing client 
  or create a new client at the same time.

U3. As a customer, I want to be able to store relevant details about my translation case, such as cost per character, 
  total payment, and whether the case is still open.

U4. As a customer, I want to update existing translation cases with details about my translation progress (word count, 
  dates, times).


#### Clients

U5. As a customer, I want to be able to view details about a client in a separate webpage.

U6. As a customer, I want to be able to edit client info.

U7. As a customer, I want to be able to add a new translation case from the client detail page.

U8. As a customer, I want to see a clickable list of all translation cases associated with a client, sorted by most 
  recent activity.

U9. As a customer, I want to see a clickable list of all translation cases associated with a client, sorted 
reverse-chronologically by creation date.  

#### Home Page

U10. As a customer, I want to see a list of open translation cases on the home page, sorted by soonest deadline.

U10. As a customer, I want to see a list of up to 10 clients on the home page, sorted by most recent activity.

U11. As a customer, I want to add a new translation case from the home page.

U12. As a customer, I want to search for a client from the home page.

U13. As a customer, I want to search for a translation case from the home page.

U14. As a customer, I want to click a link on the home page to see a clickable list of all clients sorted 
  alphabetically.

U15. As a customer, I want to click a link on the home page to see a clickable list of all clients sorted by most   
  recent activity.

U16. As a customer, I want to click a link on the home page to see a clickable list of all translation cases associated
  with a certain type of project.

U17. As a customer, I want to click a link on the home page to see a clickable list of all translation cases sorted by
  most recent activity.


### Stretch Goals

#### Estimates

U18. As a customer, I want to create a new estimate from the home page.

U19. As a customer, I want to create a new client and either associate it with an existing client or create a new 
  client at the same time.

U20. As a customer, I want to create new case estimates with estimated time needed for completion.

U21. As a customer, I want to create new case estimates with a suggested pricing based on past cases of the same type.

U22. As a customer, I want to create new case estimates that show potential time conflicts with currently open cases.


#### Statistics

U23. As a customer, I want to see useful statistics about my translation habits displayed on the home page.



## 4. Project Scope

### 4.1. In Scope

#### Translation Cases
* Create a new translation case that contains useful details
  * Must assign an existing client or create a new client during new translation case creation
* Retrieve information about existing translation cases
* Retrieve a list of all translation cases, sorted by project type
* Retrieve a list of all translation cases, sorted by most recent activity
* Update progress on existing translation cases
* Archive (soft-delete) translation cases

#### Clients
* Create a new client (must be linked to a new translation case)
* Retrieve client information
* Retrieve a list of all clients, sorted alphabetically
* Retrieve a list of all clients, sorted by most recent activity
* Retrieve all translation cases associated with a client
* Update information about the client
* Archive (soft-delete) a client

### Stretch Goals

#### Estimates
* Create a new estimate
* Save an estimate by assigning it to an existing client or by creating a new client
* Retrieve past estimates
* Retrieve a list of all estimates, sorted by project type
* Retrieve a list of all estimates, sorted by client
* Link an existing estimate to a new translation case
* Update the estimate with notes on negotiation or other details (saved estimate is read-only, notes can be edited)
* Archive (soft-delete) an estimate

#### Statistics
* Pull statistics from the translation cases
  * Words translated per hour compared by project type
  * Most productive time of day for each project type
  * Largest project type by case count
  * Total translation time since beginning of year / month
  * Clients with the fastest payment turnaround time
  * etc.


### 4.2. Out of Scope

* Linking accounts
* Storing translated documents
* Making translation keywords searchable
* Creating estimates that can be sent to customers directly

# 5. Proposed Architecture Overview

This initial iteration will provide the minimum loveable product (MLP) including creating, retrieving, updating and 
  archiving translation cases and clients, as well as sorting these objects in useful ways.

I will use API Gateway and Lambda to create seven endpoints (`GetTranslationCase`, `CreateTranslationCase`, 
`UpdateTranslationCase`, `ArchiveTranslationCase`, `GetClient`, `UpdateClient`, `ArchiveClient`) that will handle the 
creation, update, retrieval and archiving of clients and translation cases.

I will store the clients and translation cases in tables in DynamoDB.

TranslationTracker will also provide a web interface for users to manage their translation cases and clients. A main 
  page providing lists of the translation cases with the soonest upcoming deadlines and clients with most recent 
  activity will allow creation of new translation cases and link off to pages per-client and per-translation to update 
  translation progress and other details.

# 6. API

## 6.1. Public Models

```
// TranslationCaseModel

String clientId;
String translationCaseId;
String sourceTextTitle;
String sourceTextAuthor;
String translatedTitle;
String caseNickname;
Double rate;
String rateUnit;
Integer count;
String countUnit;
Double grossPayment;
Double taxRate;
String projectType;
String dueDate;
String startDate;
String endDate;
Boolean openCase;
Boolean casePaid;
Boolean rushJob;
Map<String, Map<String, String>> progressLog;
Double totalWorkingHours;
Double wordsPerHour;

// ClientModel

String clientId;
String clientName;
String clientType;
Map<String, Map<String, String>> translationCaseHistory;
Map<String, Map<String, String>> paymentHistory;
Map<String, String> outstandingPayments;
```

### Stretch Goal

```
// EstimateModel

String estimateId;
String clientId;
String clientName;
String clientType;
String sourceTextTitle;
String sourceTextAuthor;
Double estimatedRate;
String estimatedRateUnit;
Integer count;
String countUnit;
Double estimatedGrossPayment;
Double taxRate;
String projectType;
String dueDate;
Double totalWorkingHoursEstimate;
Double wordsPerHourEstimate;
```

## 6.2. Get Translation Case Endpoint

* Accepts `GET` requests to `/cases/:id`
* Accepts a `TranslationCase` ID and returns the corresponding `TranslationCaseModel`.
    * If the given `TranslationCase` ID is not found, will throw a
      `CaseNotFoundException`

## 6.3 Create Translation Case Endpoint

* Accepts `POST` requests to `/cases`
* Accepts data to create a new `TranslationCase` with a provided case nickname, a given `Client`
  ID, with all other values being optional. 
* Returns the new `TranslationCase`, including a unique `TranslationCase` ID assigned by Translation Tracker.
  * If the case nickname is identical to an already-existing case nickname, will throw an 
    `InvalidCaseNicknameException`.

## 6.4 Update Translation Case Endpoint

* Accepts `PUT` requests to `/cases/:id`
* Accepts data to update a `TranslationCase` including a `TranslationCase` ID, and the update values. Returns the updated
  `TranslationCase`.
    * If the `TranslationCase` ID is not found, will throw a `CaseNotFoundException`

## 6.5 Archive Translation Case Endpoint

* Accepts `DELETE` requests to `/cases/:id`
* Accepts a `TranslationCase` ID and archives the specified `TranslationCase`.
  * If the `TranslationCase` ID is not found, will throw a `CaseNotFoundException`

## 6.6 Get Client Endpoint

* Accepts `GET` requests to `/clients/:id`
* Accepts a `Client` ID and returns the corresponding `ClientModel`.
  * If the given `Client` ID is not found, will throw a `ClientNotFoundException`

## 6.7 Update Client Endpoint

* Accepts `PUT` requests to `/clients/:id`
* Accepts data to update a `Client` including a client ID, and the update values. Returns the updated
  `Client`.
  * If the client ID is not found, will throw a `ClientNotFoundException`

## 6.8 Archive Client Endpoint

* Accepts `DELETE` requests to `/clients/:id`
* Accepts a `Client` ID and archives the specified `Client`.
  * If the `Client` ID is not found, will throw a `ClientNotFoundException`



_Describe the behavior of the first endpoint you will build into your service API. This should include what data it requires, what data it returns, and how it will handle any known failure cases. You should also include a sequence diagram showing how a user interaction goes from user to website to service to database, and back. This first endpoint can serve as a template for subsequent endpoints. (If there is a significant difference on a subsequent endpoint, review that with your team before building it!)_

_(You should have a separate section for each of the endpoints you are expecting to build...)_


# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the dog detail page”)_
