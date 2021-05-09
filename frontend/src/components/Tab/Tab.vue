<template>
	<div>
		<ul class="nav nav-tabs">
			<li
				class="nav-tiem"
				v-for="(content, index) in list"
				:key="`${index}_tabContent`"
			>
				<router-link
					class="nav-link"
					:to="{ path: content.path, query: $route.query }"
					>{{ content.name }}</router-link
				>
			</li>
		</ul>
	</div>
</template>
<script>
export default {
	name: "Tab",
	props: {
		list: Array,
	},
};
</script>
<style scoped src="@/assets/css/Tab/Tab.css"></style>
