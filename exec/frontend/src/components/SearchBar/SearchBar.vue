<template>
	<div class="search-bar">
		<select
			v-if="filterList"
			name="filter"
			id="search-bar-filter"
			v-model="filterValue"
		>
			<option
				v-for="(value, index) in filterList"
				:key="`${index}_filterList`"
				:value="index + 1"
			>
				{{ value }}
			</option>
		</select>
		<input
			type="text"
			v-model="inputValue"
			:placeholder="placeHolderText"
			@keyup.enter="DoSearch"
		/>
		<span class="material-icons search-icon" @click="DoSearch"> search </span>
	</div>
</template>
<script>
export default {
	name: "SearchBar",
	props: {
		placeHolderText: String,
		filterList: Array,
		keyword: String,
		filter: Number,
	},
	created() {
		this.inputValue = this.keyword ? this.keyword : "";
		this.filterValue = this.filter ? this.filter : 1;
	},
	data() {
		return {
			inputValue: "",
			filterValue: 1,
		};
	},
	methods: {
		DoSearch: function () {
			this.$emit("doSearch", this.filterValue, this.inputValue);
		},
	},
};
</script>
<style scoped src="@/assets/css/SearchBar/SearchBar.css"></style>
