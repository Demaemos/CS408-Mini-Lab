# Canvas Assignment Tracker

A small Spring Boot web app that connects to the Boise State Canvas API to display your enrolled courses and their assignments, sorted by due date — built for CS408 Full Stack Dev.

## Setup Instructions

### Prerequisites
- Java 25 (or compatible) installed — check with `java -version`
- A Canvas account with API access (BSU students: use your student login)

### 1. Clone the repository
```bash
git clone https://github.com/Demaemos/CS408-Mini-Lab.git
cd CS408-Mini-Lab
```

### 2. Generate a Canvas API token
1. Log into Canvas, click your profile picture → **Settings**
2. Scroll to **Approved Integrations**
3. Click **+ New Access Token**, give it a purpose (e.g. "Canvas Mini Lab"), set an expiration, and generate it
4. Copy the token — Canvas only shows it once

### 3. Create your `.env` file
In the project root (same folder as `pom.xml`), create a file named `.env`:

Paste the following code in `.env`: 
CANVAS_API_TOKEN=your_token_here


(See `.env.example` for the expected format.)

### 4. Run the app
```bash
./mvnw spring-boot:run
```
Wait for `Started CanvasMiniLabApplication` in the terminal, then open **http://localhost:8080** in your browser.

## API Endpoints Used

| Endpoint | Purpose |
|---|---|
| `GET /api/v1/courses?enrollment_state=active` | Lists the user's currently active courses |
| `GET /api/v1/courses/{id}/assignments` | Lists assignments for a selected course, including due date and points |

Both endpoints follow Canvas's `Link` header pagination to retrieve all results, not just the first page.

## Reflection
I learned throughout this project that there is much that goes into designing, building, and implementing an actual project. I couldn't have guessed how many separate files I would've needed to get this program properly working. I will say that the most challenging parts were the initial readings and setup of the project. I felt lost while reading through the directions and got tripped up on where I had to create my access token. This led to a few delays. The setup was also tricky as some parts were made on the GitHub web page while others were made in my IDE, and it was likely possible to implement much of the skeleton in one or the other in order to reduce on back tracking, removing unnecessary files, or just correcting some management mistakes. 

It was interesting to see how with each additional step, the project would look and feel more authentic. First, it was getting the program to pass without errors, then implementing small changes like text, boxes, and spacing. Eventually, the links came next, and setting up the system to return to the main page. Then finally, I had a program where it would show some of my classes and some unnecessary class links as well. Through some trial and error, and restricted iterative prompting, I was able to make a change that filters out classes that are not listed as 'active'. This was done as I found that Canvas isn't always the most efficient when it comes to updating/purging a student's Canvas Dashboard. Past courses from previous semesters would still be present, and as a student, I found it to be useless and distracting from my active list of courses.

If I had more time with this project. I would've wanted to focus more on the visual appearance of the webpage. To find an aesthetic that would've shown that I could actually implement something unique. Additionally, I think it would've been interesting to implement a type of "priority tracker" where it would show a list of several assignments from every course and order by due date. This is something that I actively do in my own way, but not in a programmed way. It can be taxing to jump between every course page and find all the assignments that due, then go back over everything to find out which assignment should be worked on first. 