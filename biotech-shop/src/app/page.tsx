"use client";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from "react";
import MobileNav from "@/components/MobileNav";
import Navbar from "@/components/Navbar";


export default function Home() {
  const [isMobile, setIsMobile] = useState(false);
  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 768);
    };

    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <>
    {isMobile ? <MobileNav /> : <Navbar />}
    </>
  );
}
