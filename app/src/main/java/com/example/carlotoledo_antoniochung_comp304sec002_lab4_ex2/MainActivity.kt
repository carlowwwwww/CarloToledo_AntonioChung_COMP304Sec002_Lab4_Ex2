package com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Course
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Data
import com.example.carlotoledo_antoniochung_comp304sec002_lab4_ex2.data.Program


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Select Program") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                ProgramList(
                    programs = Data.programs
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}



@Composable
fun CourseItem(course: Course, onClick: () -> Unit) {
    Text(
        text = course.courseName,
        modifier = Modifier.clickable { onClick() }
    )
}







@Composable
fun ProgramList(programs: List<Program>) {
    val programCoursesMap = mutableMapOf<Program, List<Course>>()

    LazyColumn {
        items(programs) { program ->
            ProgramItem(program, programCoursesMap)
        }
    }
}

@Composable
fun CourseList() {
    LazyColumn ( modifier = Modifier.padding(start = 8.dp))
    {
        items(Data.courses) { course ->
            CourseItem(course)
        }
    }
}

@Composable
fun ProgramItem(program: Program, programCoursesMap: MutableMap<Program, List<Course>>) {
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
            Text(text = program.name, style = MaterialTheme.typography.titleLarge)
            if (expanded) {  // Show the description if the card is expanded
                Spacer(modifier = Modifier.height(8.dp))

                val programCourses = program.courses.mapNotNull { courseId ->
                    Data.courses.find { it.courseId == courseId }
                }
                programCoursesMap[program] = programCourses

                val text = programDescriptions(program, programCoursesMap)
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

fun programDescriptions(selectedProgram: Program, programMap: MutableMap<Program, List<Course>>): String {
    var programDetails = "\nProgram ID: ${selectedProgram.id}\n\n"

    var programCourses = programMap[selectedProgram]

    if (programCourses != null) {
        for (course in programCourses) {
            programDetails += "${course.courseId} : ${course.courseName}\n"
        }
    }
    return programDetails
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
