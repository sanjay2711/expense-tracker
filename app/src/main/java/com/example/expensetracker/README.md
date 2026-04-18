# 💸 Expense Tracker App

A modern, clean, and scalable **Expense Tracker Android App** built using **Jetpack Compose, Clean Architecture, and Hilt (DI)**.
This app helps users track daily expenses, analyze spending patterns, and manage finances efficiently.

---

## 🚀 Features

### ✅ Core Features

* Add, edit, and delete expenses
* Categorize transactions (Food, Travel, Bills, etc.)
* View all transactions in a structured list
* Monthly expense summary

### 📊 Analytics

* Category-wise expense breakdown
* Monthly insights
* Visual representation using charts (Pie/Bar)

### 🎨 UI/UX

* Built with **Jetpack Compose**
* Material Design 3
* Dark mode support
* Smooth and responsive UI

### ⚡ Advanced Features

* Offline-first (Room Database)
* Real-time UI updates using Flow
* Scalable architecture
* Clean and maintainable codebase

---

## 🧱 Architecture

This project follows **Clean Architecture** principles:

```
UI (Compose)
   ↓
ViewModel
   ↓
UseCases (Domain Layer)
   ↓
Repository (Interface)
   ↓
Repository Implementation (Data Layer)
   ↓
Room Database
```

### 📂 Project Structure

```
expense-tracker/
 ├── app/        # App entry point,
 ├── data/       # Room DB, DAO, Repository Implementation
 ├── di/         # DI setup (Hilt)
 ├── domain/     # Business logic, UseCases, Models
 ├── ui/         # Screens, ViewModels, UI Components
 
```

---

## 🛠️ Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **Hilt (Dependency Injection)**
* **Room Database**
* **Flow / Coroutines**
* **MVVM + Clean Architecture**

---

## 📸 Screenshots

> *(Add your app screenshots here)*

* Dashboard Screen
* Add Expense Screen
* Analytics Screen

---

## 🔄 App Flow

1. User adds an expense
2. Data is stored in Room DB
3. Flow emits updated data
4. ViewModel updates UI state
5. UI recomposes automatically

---

## 🧪 Testing

* Unit testing for UseCases
* UI testing (optional: Appium integration)

---

## 📦 Installation

1. Clone the repository:

```
git clone https://github.com/your-username/expense-tracker.git
```

2. Open in Android Studio

3. Run the app on emulator/device

---

## 🎯 Why This Project?

This project demonstrates:

* Clean Architecture in real-world apps
* Proper separation of concerns
* Scalable and maintainable code structure
* Modern Android development practices

---

## 🚀 Future Improvements

* Cloud sync (Firebase)
* Export to CSV/PDF
* Multi-device support
* Budget planning & alerts
* Authentication

---

## 👨‍💻 Author

**Sanjay S**

* GitHub: https://github.com/sanjay2711/expense-tracker/tree/main/app/src/main/java/com/example/expensetracker  
* LinkedIn: https://www.linkedin.com/in/sanjay-saravanan-849234235?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=ios_app

---

## ⭐ Show Your Support

If you like this project, give it a ⭐ on GitHub!

---
