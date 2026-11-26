from pathlib import Path
import shutil, zipfile

base = Path("/mnt/data/smart-lms-readme")
images = base / "screenshots"
images.mkdir(parents=True, exist_ok=True)

sources = [
    Path("/mnt/data/image(20261126-151218).png"),
    Path("/mnt/data/image(20261126-151245).png"),
    Path("/mnt/data/image(20261126-151307).png"),
]
names = ["dashboard.png", "classes.png", "students.png"]

for src, name in zip(sources, names):
    if not src.exists():
        raise FileNotFoundError(f"Could not access uploaded screenshot: {src}")
    shutil.copy2(src, images / name)

readme = """# Smart Learning Management System (Smart LMS)

A desktop-based Learning Management System designed to help an administrator manage classes, students, and learning activities from one place.

## Application Preview

### 1. Dashboard
The dashboard provides a quick overview of the LMS, including total classes, total students, the current learning status, and a preview of available classes.

![Smart LMS Dashboard](screenshots/dashboard.png)

### 2. Classes
The Classes section displays the available courses. Administrators can create classes and remove existing classes.

![Smart LMS Classes](screenshots/classes.png)

### 3. Students
The Students section displays registered learners and their email addresses. Administrators can add students and manage the student list.

![Smart LMS Students](screenshots/students.png)

## Main Features

- **Dashboard overview** — view class and student totals at a glance.
- **Class management** — browse available classes, create a class, and remove classes.
- **Student management** — view registered students and their email addresses, and add students.
- **Navigation sidebar** — move between the main LMS sections.
- **Administrator-focused interface** — centralised management through an admin view.

## Interface Sections

The navigation shown in the application includes:

- Dashboard
- Classes
- Students
- Study Materials
- Assignments
- Examinations
- Attendance

> The screenshots document the Dashboard, Classes, and Students screens. The remaining navigation items are listed as sections visible in the interface; their detailed functionality is not demonstrated by these screenshots.

## Screenshots

All screenshots are stored in the [`screenshots/`](screenshots/) folder. Keep the folder alongside this README so the images render correctly on GitHub and in local Markdown viewers.

## Project Information

- **Project name:** Smart Learning Management System (Smart LMS)
- **Application type:** Desktop application
- **User role shown:** Admin

## Getting Started

Open the project in the development environment used to build the application and follow its run instructions. Add the exact language/runtime version and launch command here once the project setup is finalised.

## Future Improvements

Potential areas for further development include:

- Complete management workflows for study materials, assignments, examinations, and attendance.
- Persistent storage for classes, students, and other administrative changes.
- Search, filtering, and editing options for records.
- Role-based access for administrators, teachers, and students.

---

*README prepared using the supplied Smart LMS application screenshots.*
"""
(base / "README.md").write_text(readme, encoding="utf-8")

zip_path = Path("/mnt/data/Smart-LMS-README-with-Screenshots.zip")
with zipfile.ZipFile(zip_path, "w", compression=zipfile.ZIP_DEFLATED) as zf:
    for p in base.rglob("*"):
        if p.is_file():
            zf.write(p, p.relative_to(base.parent))

print("Created:")
print(base / "README.md")
print(zip_path)
print("Included screenshots:", ", ".join(names))
