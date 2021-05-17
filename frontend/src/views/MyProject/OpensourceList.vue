<template>
	<div class="container">
		<mapped-opensource-list></mapped-opensource-list>
		<un-mapped-opensource-list></un-mapped-opensource-list>
	</div>
</template>
<script>
import "@/assets/css/MyProject/OpensourceList.css";
import MappedOpensourceList from "../../components/MyProject/MappedOpensourceList.vue";
import UnMappedOpensourceList from "../../components/MyProject/UnMappedOpensourceList.vue";
export default {
	name: "OpensourceList",
	components: { MappedOpensourceList, UnMappedOpensourceList },
};
</script>
<style scoped src=""></style>
