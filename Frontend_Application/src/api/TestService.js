import axios from "axios";

const API_URL = "http://localhost:8080";

// just testing stuff here
export async function getBackendProject() {
  return await axios.post(API_URL);
}
