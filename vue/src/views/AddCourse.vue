<template>
  <div id="add-course">
      <h1 id="add-course-title">Add New Course</h1>
      <div id="add-course-form">
        <AddCourseForm />
      </div>
  </div>
  
</template>

<script>
import AddCourseForm from "../components/AddCourseForm.vue";

export default {
    name: "add-course",
    components: {
        AddCourseForm
    }

}
</script>

<style>
#add-course {
    display: flex;
}
#add-course-title {
    display:flex;
    justify-content: center;
    background: rgba(186,199,202,.9);
    margin: 5px 5px 0 5px;
    padding-left: 5px;
}
#add-course-form{
    margin: 0 5px 0 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas: 
        "blank" "title" "title" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "option" "input" "blank"
        "blank" "submit" "cancel" "blank"
    ;
}
#add-course-form label {
    grid-area: option;
}
</style>