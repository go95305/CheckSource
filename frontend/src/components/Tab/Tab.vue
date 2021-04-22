<template>
  <div>
    <ul>
      <li v-for="(content, index) in list" :key="`${index}_tabContent`">
        <input type="radio" :id="`${index}_` + name" :name="name" :value="index" v-model="tabValue" />
        <label :for="`${index}_` + name"> {{ content }}</label>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: "Tab",
  props: {
    name: String,
    list:Array,
  },
  data() {
    return {
      tabValue : 0,
    };
  },
  watch:{
      tabValue:function(){
          this.$emit("tabValueChange",this.tabValue);
      }
  }
};
</script>
<style src="@/assets/css/Tab/Tab.css"></style>
