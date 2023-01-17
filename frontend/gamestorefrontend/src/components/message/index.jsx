import React, { useEffect, useState } from "react";
import ReactDOM from "react-dom";

import "./index.less";

const div = document.createElement("div");
document.body.appendChild(div);

function notice(args) {
  return ReactDOM.render(<Message {...args} />, div);
}

let timer;

function Message(props) {
  const [msgs, setMsgs] = useState([]);
  const { content, type } = props;

  const iconObj = {
    info: "mf-icon-information",
    success: " mf-icon-selected2",
    warn: "mf-icon-Prompt",
    error: " mf-icon-remove",
  };

  useEffect(() => {
    setMsgs([...msgs, props]);
  }, [props]);

  useEffect(() => {
    if (msgs.length) {
      let msgscopy = JSON.parse(JSON.stringify(msgs));

      // setInterval 写法
      clearInterval(timer);
      timer = setInterval(
        (setMsgs) => {
          msgscopy.shift();
          setMsgs(JSON.parse(JSON.stringify(msgscopy)));
          if (!msgscopy.length) {
            clearInterval(timer);
          }
        },
        props.duration * 1000,
        setMsgs
      );

    }
  }, [msgs]);

  return (
    <div className="message">
      {msgs.map((item, index) => {
        return (
          <div className="message-content" key={index}>
            <i className={`${iconObj[type]} message-content-icon` }></i>
            <span className="message-content-text">{content}</span>
          </div>
        );
      })}
    </div>
  );
}

let api = {};

["info", "success", "warn", "error"].forEach((type) => {
  api[type] = (content, duration = 3) => {
    return notice({ content, duration, type });
  };
});

export default api;