<template>
<<<<<<< HEAD
  <div id="add-course">
      <h1 id="add-course-title">Add New Course</h1>
      <!-- <div id="add-course-form"> -->
      <AddCourseForm id="add-course-form"/>
      <!-- </div> -->
=======
  <div class="add-course">
        <h1 id="add-course-title">Add New Course</h1>
        <AddCourseForm />
>>>>>>> main
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
<<<<<<< HEAD
#add-course-title {
    display:flex;
    justify-content: center;
    background: rgba(186,199,202,.9);
    border-radius: 5px;
    margin: 5px;
}
#add-course-form{
    margin-left: 250px;
    margin-right: 250px;
    padding: 10px;
    background-color: rgba(186,199,202,.9);
    border-radius: 5px;
=======
.add-course {
    margin: 5px 5px 5px 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
    text-shadow: 1px 1px 1px #e7e7e7;
}
#add-course-title {
>>>>>>> main
    display: flex;
    justify-content: center;
    margin: 4% auto 6%;
    font-size: 50px;
    text-decoration: underline 3px;
}
</style>

