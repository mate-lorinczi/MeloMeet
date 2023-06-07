import { useState } from "react";
import GroupForm from "../components/GroupForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const postGroup = async (values) => {
  console.log(JSON.stringify(values));
  const res = await fetch("/api/groups", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  });

  return res.status;
};

const getStatusText = (code) => {
  const statusMap = {
    201: "Group created!",
    404: "Concert not found!",
  };
  return statusMap[code];
};

const NewGroup = () => {
  const [events, loading] = useAsyncFetch("/api/events/all");
  const [statusMsg, setStatusMsg] = useState(null);

  const submit = async(values) => {
    const userId = localStorage.getItem("userId");
    const finalValues = { creatorId: userId, ...values };
    const statusCode = await postGroup(finalValues);
    setStatusMsg(getStatusText(statusCode));
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <GroupForm events={events} submit={submit} />
      {statusMsg ? <div>{statusMsg}</div> : null}
    </div>
  );
};

export default NewGroup;
