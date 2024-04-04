package com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Course
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Data
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Program
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var expanded by remember { mutableStateOf(false) }
    var selectedProgram by remember { mutableStateOf<Program?>(null) }

    Column {
        Box(
            modifier = Modifier
                .widthIn(max = 240.dp) // Limit dropdown width
                .clip(MaterialTheme.shapes.medium) // Rounded corners
                .background(Color.LightGray) // Background color
                .padding(vertical = 4.dp) // Add vertical padding
                .wrapContentSize(Alignment.Center) // Center content horizontally
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(IntrinsicSize.Min) // Match parent width
            ) {
                (Data.programs).forEach { program ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = program.name,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedProgram = program
                            expanded = false
                        },
                    )
                }
            }
        }

        Text(
            style = MaterialTheme.typography.titleLarge,
            text = selectedProgram?.name ?: "Select a program",
            modifier = Modifier
                .padding(20.dp)
                .clickable { expanded = true }
                .background(Color.LightGray),
            textAlign = TextAlign.Center // Center text horizontally
        )

        if (selectedProgram != null) {
            val programCoursesMap = mutableMapOf<Program, List<Course>>()
            val programCourses = selectedProgram!!.courses.mapNotNull { courseId ->
                Data.courses.find { it.courseId == courseId }
            }
            programCoursesMap[selectedProgram!!] = programCourses

            programCoursesMap[selectedProgram]?.let { CourseList(courses = it) }
        }
    }
}

@Composable
fun CourseList(courses: List<Course>) {
    LazyColumn ( modifier = Modifier.padding(start = 8.dp))
    {
        items(courses) { course ->
            CourseItem(course)
        }
    }
}

@Composable
fun CourseItem(course: Course) {
    var expanded by remember { mutableStateOf(false) }  // Track whether the card is expanded

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                expanded = !expanded
            },  // Toggle the expanded state when the card is clicked
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${course.courseId} : ${course.courseName}\n", style = MaterialTheme.typography.titleLarge)
            if (expanded) {  // Show the description if the card is expanded
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
