<template>
  <div class="add-course">
      <div id="add-course-form">
        <h1 id="add-course-title">Add New Course</h1>
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
.add-course {
    margin: 5px 5px 0 5px;
    padding-left: 5px;
    background-color: rgba(186,199,202,.9);
}
#add-course-title {
    display: flex;
    justify-content: center;
    
}
</style>

<!-- display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas: 
        "title title title title"
        "blank option input blank"
        "blank option input blank"
        "blank option input blank"
        "blank option input blank"
        "blank option input blank"
        "blank option input blank"
        "blank option input blank"
        "blank submit cancel blank"
    ;
}
#add-course-form #add-course-title {
    margin: 0px;
    grid-area: title;
}
#add-course-form label {
    grid-area: option;
}
#add-course-form input {
    grid-area: input;
}
#add-course-form .btn btn-submit {
    grid-area: submit;
}
#add-course-form .btn btn-cancel {
    grid-area: cancel;
} -->