<template>
  <div class="add-course">
        <h1 id="add-course-title">Add New Course</h1>
        <AddCourseForm />
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
.add-course {
    margin: 5px 5px 5px 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    border-radius: 5px;
    text-shadow: 1px 1px 1px #e7e7e7;
}
#add-course-title {
    display: flex;
    justify-content: center;
    margin: 3%;
    font-size: 50px;
    text-decoration: underline 3px;
}
</style>

