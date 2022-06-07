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
#add-course-title {
    display:flex;
    justify-content: center;
    background: rgba(186,199,202,.9);
    border-radius: 5px;
    margin: 5px;
}
#add-course-form{
    margin: 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    border-radius: 5px;
    display: flex;
    flex-direction: column;
}
</style>