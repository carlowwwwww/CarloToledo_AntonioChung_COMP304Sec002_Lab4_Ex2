package com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data

object Data {
    val programs = listOf(
        Program(3402, "Artificial Intelligence (Optional Co-op)", listOf("COMP-214", "COMP-216", "COMP-247", "COMP-254", "COMP-311")),
        Program(3404, "Game - Programming (Optional Co-op)", listOf("COMP-254", "COMP-311", "COMP-397", "MATH-210")),
        Program(3409, "Software Engineering Technology (Optional Co-op)", listOf("COMP-212", "COMP-216", "COMP-254", "COMP-304", "COMP-311", "MATH-210")),
    )

    val courses = listOf(
        Course("COMP-214", "Advanced Database Concepts", "This course is intended to expand the student’s knowledge of business databases using data RDBMS and NoSQL driven systems. The course introduces students to the steps required to install and configure a database server and development system."),
        Course("COMP-216", "Networking for Software Developers", "Learners in this course will gain hands-on experience by applying knowledge of network protocols and components to the development and maintenance of software applications."),
        Course("COMP-247", "Supervised Learning", "In this course, students will be introduced to supervised learning techniques and algorithms. Coursework covers the following algorithms: linear regression, logistic regression, decision trees, bayesian learning, support vector machines, sequence learning, k-nearest neighbors, and ensemble techniques."),
        Course("COMP-311", "Software Testing and Quality Assurance", "This course explores the goals of quality assurance and quality control activities performed during the life cycle of a software product. It focuses on integrating test processes with agile software development methodologies. "),
        Course("COMP-397", "Web Game Programming", "This course will cover a basic understanding of the modern tools and development methodologies used for creating industry standard Web and Mobile Games. The course has a hands-on, programming and development approach and it uses a Commercial Game Engine."),
        Course("COMP-254", "Data Structures and Algorithms", "Building on fundamentals of Object-Oriented programming, this course exposes the students to algorithms and data structures. Students will analyze, evaluate and apply appropriate data structures & algorithms for the implementation of a software system."),
        Course("MATH-210", "Linear Algebra and Statistics", "This course contains topics in Linear Algebra and Statistics. Linear algebra topics include operations with matrices, inverses, determinants, and vectors. "),
        Course("COMP-212", "Programming 3", "The goal of this course is to enable students, already proficient in OOP, to build robust and more complex, data-driven desktop applications using the .NET technologies."),
        Course("COMP-304", "Mobile Apps Development", "In this mobile apps course, students will gain hands-on experience in developing and deploying mobile applications on the Android platform. ")
        )
}