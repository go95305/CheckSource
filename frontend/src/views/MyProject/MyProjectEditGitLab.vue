<template>
    <div class="myproject-edit-gitlab">
        <repository-card
            v-for="(repository, index) in repositoryList"
            :key="`${index}_repositoryList`"
            :index="index"
            :repository="repository"
            :selected="false"
            :checked="IsSelected(repository.project_id)"
            @addRepoClick="AddRepoClick"
        ></repository-card>
    </div>
</template>
<script>
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
export default {
    components: { RepositoryCard },
    name: "MyProjectEditGitLab",
    data() {
        return {
            repositoryList: [],
        };
    },
    props: {
        selectedRepositoryList: Array,
    },
    created() {
        this.GetRepositories();
    },
    methods: {
        GetRepositories: function () {
            //레포지토리 얻어오기
            this.repositoryList = [
                {
                    project_id: "111",
                    name: "project1",
                    gittype: 2,
                },
                {
                    project_id: "112",
                    name: "project2",
                    gittype: 2,
                },
            ];
        },
        IsSelected: function (project_id) {
            //선택된 레포지토리인지 확인
            if (
                this.selectedRepositoryList.findIndex(
                    (i) => i.project_id == project_id
                ) == -1
            ) {
                return false;
            }
            return true;
        },
        AddRepoClick: function (repo) {
            this.$emit("addRepoClick", repo);
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEditGitLab.css"></style>
