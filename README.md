# Identify_Courses
## Hackathon project as part of training in Quality engineer with selenium automation


# Web Development Course Automation - Hackathon Project

## 🧠 Problem Statement

Automate the process of searching and extracting beginner-level web development courses in English, analyzing language learning options, and interacting with Coursera's enterprise form.

---

## 🔍 Task Breakdown

### 1. Search and Display Web Development Courses

**Criteria:**
- Beginner level
- English language
- Extract first two courses with:
  - Course Name
  - Total Learning Hours
  - Rating

**Source:** Coursera.org

**Extracted Courses:**

1. **Meta Front-End Developer**
   - **Duration:** 3 - 6 Months
   - **Rating:** 4.7 (22K+ reviews)

2. **Introduction to Web Development** by University of California, Davis
   - **Duration:** 1 - 4 Weeks
   - **Rating:** 4.7 (3.7K+ reviews)

---

### 2. Language Learning Analysis

- Navigate to Coursera's Language Learning section
- Extract:
  - All available languages
  - Levels (Beginner, Intermediate, Advanced)
  - Total count per level

---

### 3. Enterprise Form Automation

- Navigate to **Home > For Enterprise > Campus**
- Under **Product**, locate **Courses for Campus**
- Fill the **"Ready to transform"** form with one invalid input (e.g., incorrect email format)
- Capture and display the **error message**

---

## 🛠️ Key Automation Scope

- Handle multiple browser windows and tabs
- Use search functionality dynamically
- Extract and store dropdown list items
- Navigate back to the homepage
- Fill forms with various input types
- Capture and display warning/error messages
- Scroll through web pages as needed

---

## 💡 Tools & Technologies

- Selenium / Playwright for browser automation
- Python for scripting
- GitHub for version control
- Markdown for documentation

---


