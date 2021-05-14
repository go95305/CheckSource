<template>
	<div class="container">
		<mapped-opensource-list></mapped-opensource-list>
		<un-mapped-opensource-list></un-mapped-opensource-list>
		<!-- <div class="component-specific">
				<div class="specific-row">
					<p class="specific-title">License Version:</p>
					<p>{{ item.version }}</p>
				</div>
				<div class="specific-row">
					<p class="specific-title">Packge Type:</p>
					<p>{{ item.packageType }}</p>
				</div>
				<div class="specific-row">
					<p class="specific-title">Artifact Id:</p>
					<p>{{ item.artifactId }}</p>
				</div>
				<div class="specific-row">
					<p class="specific-title">Group Id:</p>
					<p>{{ item.groupId }}</p>
				</div>
			</div> -->
	</div>
</template>
<script>
import "@/assets/css/MyProject/OpensourceList.css";
import verifyApi from "@/api/verify.js";
import MappedOpensourceList from "../../components/MyProject/MappedOpensourceList.vue";
import UnMappedOpensourceList from "../../components/MyProject/UnMappedOpensourceList.vue";
export default {
	components: { MappedOpensourceList, UnMappedOpensourceList },
	data() {
		return {
			projectId: this.$route.query.projectId,
			sidebarShow: false,
			containerWidth: false,
			version: "",
			packageType: "",
			artifactId: "",
			groupId: "",

			unmappedList: [
				{
					name: "Apache License 2.0",
					origin: "http://www.apache.org/licenses/LICENSE-2.0",
				},
				{
					name: "GNU General Public License v2.0 or later",
					origin: "https://opensource.org/licenses/BSD-3-Clause",
				},
				{
					name: "Eclipse Public License 2.0",
					origin: "https://www.eclipse.org/legal/epl-2.0",
				},
			],
		};
	},
	created() {
		this.getList();
	},
	methods: {
		getList: function () {
			verifyApi.readVerifiedOpenSourceList(this.projectId).then((response) => {
				if (response.data) {
					console.log(response.data);
					this.mappedList = response.data.mappedList;
					this.unmappedList = response.data.unmappedList;
				}
			});
		},
		goOpenSource: function (id) {
			this.$router.push({
				name: "OSSDetailOpenSource",
				query: { id: id },
			});
		},
		sidebar(event) {
			let elem = event.target;
			while (!elem.classList.contains("table-row")) {
				elem = elem.parentNode;

				if (elem.nodeName == "BODY") {
					elem = null;
					return;
				}
			}

			if (elem.classList.contains("choice")) {
				elem.classList.remove("choice");
			} else {
				elem.classList.add("choice");
			}
		},
		closeSideBar() {
			this.sidebarShow = false;
			this.containerWidth = false;
		},
		addComponent() {
			this.$router.push({ name: "AddOpenSource" });
		},
	},
};
</script>
<style scoped src=""></style>
