<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addUser">Add User</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="users"
        :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editUser(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteUser(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <UserDialog
        v-bind:opened="dialogVisible"
        :user="selectedItem"
        @refresh="refreshList"
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog";

export default {
  name: "UserList",
  components: { UserDialog },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        {
          text: "Username",
          align: "start",
          sortable: true,
          value: "name",
        },
        { text: "Email", value: "email" },
        { text: "Role", value: "roles[0]"},
        { text: "Actions", value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editUser(user) {
      this.selectedItem = user;
      this.dialogVisible = true;
    },
    async deleteUser(user) {
      await api.users.delete(user);
      this.message = "Delete of user succesful!";
      await this.refreshList();

    },
    addUser() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.users = await api.users.allUsers();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>