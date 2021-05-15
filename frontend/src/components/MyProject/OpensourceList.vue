<template>
    <div class="container">
        <div class="mapped-component">
            <p>Mapped OpenSource</p>
        </div>
        <div class="mapped-table-header">
            <div class="title title-1">Name</div>
            <div class="title title-2">Url</div>
            <div class="title title-2">Path</div>
            <div class="title title-3">License</div>
            <div class="title title-3">Copyright</div>
        </div>
        <ul
            class="responsive-table"
            :key="`${index}_mapped`"
            v-for="(item, index) in mappedList"
            :class="{ sideBar: containerWidth }"
        >
            <li class="table-row" @click="goOpenSource(item.opensourceId)">
                <div class="col col-1">{{ item.name }}</div>
                <div class="col col-2">{{ item.url }}</div>
                <div class="col col-2">{{ item.path }}</div>
                <div
                    v-for="(licenseName, index) in item.licenseNameList"
                    :key="`${index}_licenseNameList`"
                    class="col col-3"
                >
                    {{ licenseName }}
                </div>
                <div class="col col-3">{{ item.copyright }}</div>
            </li>

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
        </ul>
        <div class="unmapped-component">
            <p>Unmapped OpenSource</p>
        </div>
        <button class="add-component" @click="addComponent">
            OpenSource 추가
        </button>
        <div class="unmapped-table-header" :class="{ sideBar: containerWidth }">
            <div class="title utitle-1">ArtifactId</div>
            <div class="title utitle-1">GroupId</div>
            <div class="title utitle-2">Path</div>
            <div class="title utitle-2">Version</div>
        </div>
        <ul
            class="responsive-table"
            :key="`${idx}_unmapped`"
            v-for="(item, idx) in unmappedList"
        >
            <li class="table-row">
                <div class="col ucol-1">{{ item.artifactId }}</div>
                <div class="col ucol-1">{{ item.groupId }}</div>
                <div class="col ucol-2">{{ item.path }}</div>
                <div class="col ucol-2">{{ item.version }}</div>
            </li>
        </ul>
        <div class="component-specific" v-if="sidebarShow">
            <div>
                <button class="closebtn" @click="closeSideBar">&times;</button>
            </div>
            <div class="component-version">
                <p>License Version</p>
                <p class="version">{{ version }}</p>
            </div>
        </div>
    </div>
</template>
<script>
import verifyApi from "@/api/verify.js";
export default {
    data() {
        return {
            projectId: this.$route.query.projectId,
            sidebarShow: false,
            containerWidth: false,
            version: "",
            packageType: "",
            artifactId: "",
            groupId: "",
            mappedList: [],
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
            verifyApi
                .readVerifiedOpenSourceList(this.projectId)
                .then((response) => {
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
<style scoped src="../../assets/css/MyProject/OpensourceList.css"></style>
